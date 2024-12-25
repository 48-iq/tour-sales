import { defineStore } from 'pinia'
import { ref } from 'vue'
import { host } from './host'
import axios from 'axios'

export type UserCategory = {
  title: string
  description: string
}

export const useUserCategoriesStore = defineStore('userCategories', () => {
  const userCategories = ref<UserCategory[]>([])
  const isError = ref<boolean>(false)
  const isLoading = ref<boolean>(false)

  const createUserCategory = async (category: UserCategory) => {
    try {
      isLoading.value = true
      isError.value = false
      const response = await axios.post<UserCategory[]>(
        `${host}/api/user-categories/create`,
        category,
      )
      if (response.status === 200) {
        userCategories.value = response.data
        console.log(userCategories.value)
      } else {
        isError.value = true
      }
    } catch (error) {
      if (error instanceof Error) isError.value = true
    }
    isLoading.value = false
  }

  const deleteUserCategory = async (title: string) => {
    try {
      isLoading.value = true
      isError.value = false
      const response = await axios.delete<UserCategory[]>(
        `${host}/api/user-categories/delete/${title}`,
      )
      if (response.status === 200) {
        userCategories.value = response.data
      } else {
        isError.value = true
      }
    } catch (error) {
      if (error instanceof Error) isError.value = true
    }
    isLoading.value = false
  }

  const fetchUserCategories = async () => {
    try {
      isLoading.value = true
      isError.value = false
      const response = await axios.get<UserCategory[]>(`${host}/api/user-categories/all`)
      if (response.status === 200) {
        userCategories.value = response.data
      } else {
        isError.value = true
      }
    } catch (error) {
      if (error instanceof Error) isError.value = true
    }
    isLoading.value = false
  }

  return {
    userCategories,
    createUserCategory,
    deleteUserCategory,
    isError,
    isLoading,
    fetchUserCategories,
  }
})

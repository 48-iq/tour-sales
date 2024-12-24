import { defineStore } from 'pinia'
import { ref } from 'vue'
import { type User } from './userStore'
import axios from 'axios'
import { host } from './host'
import { useAuthStore } from './authStore'

export const useUserUpdateStore = defineStore('userUpdate', () => {
  const defaultUser = {
    id: '',
    username: '',
    name: '',
    surname: '',
    email: '',
    birthDate: '',
    phone: '',
  }

  const user = ref<User>(defaultUser)
  const isError = ref<boolean>(false)
  const isLoading = ref<boolean>(false)

  const authStore = useAuthStore()

  const fetchUser = async (id: string) => {
    isLoading.value = true
    isError.value = false
    const response = await axios.get<User>(`${host}/users/${id}`)
    if (response.status === 200) {
      user.value = response.data
    } else {
      isError.value = true
    }
    isLoading.value = false
  }

  const saveUser = async (data: User) => {
    isLoading.value = true
    isError.value = false
    const response = await axios.put<User>(`${host}/users/${data.id}`, data)
    if (response.status === 200) {
      user.value = response.data
    } else {
      isError.value = true
    }
    isLoading.value = false
  }

  const deleteUser = async (id: string) => {
    isLoading.value = true
    isError.value = false
    const response = await axios.delete<User>(`${host}/users/${id}`)
    if (response.status === 200) {
      user.value = defaultUser
      authStore.logout()
    } else {
      isError.value = true
    }
    isLoading.value = false
  }

  return { user, isError, isLoading, fetchUser, saveUser, deleteUser }
})

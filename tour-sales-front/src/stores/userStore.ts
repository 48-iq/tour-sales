import axios from 'axios'
import { defineStore } from 'pinia'
import { ref } from 'vue'
import { host } from './host'
import { useAuthStore } from './authStore'

export type User = {
  id: string
  username: string
  name: string
  surname: string
  email: string
  birthDate: string
  phone: string
  categories: {
    title: string
    description: string
  }[]
}

export const useUserStore = defineStore('user', () => {
  const user = ref<User>({
    id: '',
    username: '',
    name: '',
    surname: '',
    email: '',
    birthDate: '',
    phone: '',
    categories: [],
  })
  const isError = ref<boolean>(false)
  const isLoading = ref<boolean>(false)

  const authStore = useAuthStore()

  const isCurrentUser = () => {
    return user.value.id === authStore.authData.id
  }

  const fetchUser = async (id: string) => {
    isLoading.value = true
    isError.value = false
    const response = await axios.get<User>(`${host}/api/users/${id}`)
    console.log(response.status)
    if (response.status === 200) {
      user.value = response.data
    } else {
      isError.value = true
    }
    isLoading.value = false
  }

  return { user, isError, isLoading, fetchUser, isCurrentUser }
})

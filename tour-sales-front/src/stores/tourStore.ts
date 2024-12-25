import { defineStore } from 'pinia'
import { ref } from 'vue'
import type { Tour } from '@/stores/toursStore'
import { useAuthStore } from './authStore'
import axios from 'axios'
import { host } from './host'

export const useTourStore = defineStore('tour', () => {
  const tour = ref<Tour>({
    id: '',
    title: '',
    description: '',
    price: 0,
    startAt: '',
    finishAt: '',
    createdAt: '',
    companyId: '',
    cities: [],
  })
  const isError = ref<boolean>(false)
  const isLoading = ref<boolean>(false)

  const authStore = useAuthStore()

  const fetchTour = async (id: string) => {
    isLoading.value = true
    isError.value = false
    try {
      const response = await axios.get<Tour>(`${host}/api/tours/${id}`, {
        headers: { Authorization: `Bearer ${authStore.authData.jwt}` },
      })
      if (response.status === 200) {
        tour.value = response.data
      } else {
        isError.value = true
      }
    } catch (error) {
      if (error instanceof Error) {
        isError.value = true
      }
    }
  }

  const updateTour = async (data: Tour) => {
    isError.value = false
    isLoading.value = true
    try {
      const response = await axios.put<Tour>(`${host}/api/tours/update/${data.id}`, data, {
        headers: { Authorization: `Bearer ${authStore.authData.jwt}` },
      })
      if (response.status === 200) {
        tour.value = response.data
      } else {
        isError.value = true
      }
    } catch (error) {
      if (error instanceof Error) {
        isError.value = true
      }
    }
  }

  return { tour, isError, isLoading, fetchTour, updateTour }
})

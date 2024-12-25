import { defineStore } from 'pinia'
import { ref } from 'vue'
import { useAuthStore } from './authStore'
import { host } from './host'
import axios from 'axios'
import type { City } from '@/stores/citiesStore'

export type Tour = {
  id: string
  title: string
  description: string
  price: number
  startAt: string
  finishAt: string
  createdAt: string
  companyId: string
  cities: City[]
}

export const useToursStore = defineStore('tours', () => {
  const tours = ref<Tour[]>([])
  const isError = ref<boolean>(false)
  const isLoading = ref<boolean>(false)

  const authStore = useAuthStore()

  const createTour = async (tour: {
    title: string
    description: string
    price: number
    startAt: string
    finishAt: string
    availableCount: number
    companyId: string
  }) => {
    isLoading.value = true
    isError.value = false
    try {
      const response = await axios.post<Tour>(`${host}/api/tours/create`, tour, {
        headers: { Authorization: `Bearer ${authStore.authData.jwt}` },
      })
      if (response.status === 200) {
      } else {
        isError.value = true
      }
    } catch (error) {
      if (error instanceof Error) isError.value = true
    }
    isLoading.value = false
  }

  const deleteTour = async (id: string) => {
    isLoading.value = true
    isError.value = false
    try {
      const response = await axios.delete<Tour>(`${host}/api/tours/delete/${id}`, {
        headers: { Authorization: `Bearer ${authStore.authData.jwt}` },
      })
      if (response.status === 200) {
        tours.value = tours.value.filter((tour) => tour.id !== id)
      } else {
        isError.value = true
      }
    } catch (error) {
      if (error instanceof Error) isError.value = true
    }
    isLoading.value = false
  }

  const fetchTours = async () => {
    isLoading.value = true
    isError.value = false
    try {
      const response = await axios.get<Tour[]>(`${host}/api/tours/all`)
      if (response.status === 200) {
        tours.value = response.data
      } else {
        isError.value = true
      }
    } catch (error) {
      if (error instanceof Error) isError.value = true
    }
    isLoading.value = false
  }

  const fetchToursByCompanyId = async (companyId: string) => {
    isLoading.value = true
    isError.value = false
    try {
      const response = await axios.get<Tour[]>(`${host}/api/tours/by-company/${companyId}`, {
        headers: { Authorization: `Bearer ${authStore.authData.jwt}` },
      })
      if (response.status === 200) {
        tours.value = response.data
      } else {
        isError.value = true
      }
    } catch (error) {
      if (error instanceof Error) isError.value = true
    }
    isLoading.value = false
  }

  const fetchToursByCityAndTitle = async (city: string, title: string) => {
    isLoading.value = true
    isError.value = false
    try {
      const response = await axios.get<Tour[]>(`${host}/api/tours/find`, {
        headers: { Authorization: `Bearer ${authStore.authData.jwt}` },
        params: { city, query: title },
      })
      if (response.status === 200) {
        tours.value = response.data
      } else {
        isError.value = true
      }
    } catch (error) {
      if (error instanceof Error) isError.value = true
    }
    isLoading.value = false
  }

  return {
    tours,
    isError,
    isLoading,
    createTour,
    deleteTour,
    fetchTours,
    fetchToursByCompanyId,
    fetchToursByCityAndTitle,
  }
})

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
  availableCount: number
  company: {
    id: string
    name: string
    description: string
    email: string
  }
  cities: City[]
  discounts: {
    tourId: string
    categoryName: string
    discount: number
  }[]
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
        console.log(response.data)
        tours.value.push(response.data)
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
        console.log(tours.value)
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
        console.log(tours.value)
        console.log({
          action: 'in fetch tours',
          response: response.data,
          tours: tours.value,
        })
      } else {
        isError.value = true
      }
    } catch (error) {
      if (error instanceof Error) isError.value = true
    }
    isLoading.value = false
  }

  const fetchToursByTitleAndCompanyId = async (title: string, companyId: string) => {
    isLoading.value = true
    isError.value = false
    try {
      console.log({ title, companyId })
      const response = await axios.get<Tour[]>(
        `${host}/api/tours/find?title=${title}&companyId=${companyId}`,
      )
      console.log(response.data)
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
    fetchToursByTitleAndCompanyId,
  }
})

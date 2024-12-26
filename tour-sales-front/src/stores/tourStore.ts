import { defineStore } from 'pinia'
import { ref } from 'vue'
import type { Tour } from '@/stores/toursStore'
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
    availableCount: 0,
    company: { id: '', name: '', description: '', email: '' },
    cities: [],
    discounts: [],
  })
  const isError = ref<boolean>(false)
  const isLoading = ref<boolean>(false)

  const addCity = async (id: string, cityName: string) => {
    isError.value = false
    isLoading.value = true
    try {
      const response = await axios.post(`${host}/api/tours/add-city/${id}/${cityName}`, null, {})
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
    fetchTour(id)
    isLoading.value = false
  }

  const removeCity = async (id: string, cityName: string) => {
    isError.value = false
    isLoading.value = true
    try {
      const response = await axios.delete(`${host}/api/tours/remove-city/${id}/${cityName}`, {})
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
    fetchTour(id)
    isLoading.value = false
  }

  const createDiscount = async (id: string, categoryName: string, discount: number) => {
    isError.value = false
    isLoading.value = true
    try {
      const response = await axios.post(`${host}/api/discounts/create/`, {
        tourId: id,
        categoryName,
        discount,
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
    fetchTour(id)
    isLoading.value = false
  }

  const removeDiscount = async (id: string, categoryName: string) => {
    isError.value = false
    isLoading.value = true
    try {
      const response = await axios.delete(`${host}/api/discounts/delete/${categoryName}/${id}`)
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
    fetchTour(id)
    isLoading.value = false
  }

  const fetchTour = async (id: string) => {
    isLoading.value = true
    isError.value = false
    try {
      const response = await axios.get<Tour>(`${host}/api/tours/by-id/${id}`)
      if (response.status === 200) {
        tour.value = response.data
        console.log(tour.value)
      } else {
        isError.value = true
      }
    } catch (error) {
      if (error instanceof Error) {
        isError.value = true
      }
    }
    isLoading.value = false
  }

  const updateTour = async (data: {
    id: string
    title: string
    description: string
    price: number
    startAt: string
    finishAt: string
    availableCount: number
  }) => {
    isError.value = false
    isLoading.value = true
    try {
      const response = await axios.put<Tour>(`${host}/api/tours/update/${data.id}`, data, {})
      if (response.status === 200) {
      } else {
        isError.value = true
      }
    } catch (error) {
      if (error instanceof Error) {
        isError.value = true
      }
    }
    isLoading.value = false
    fetchTour(data.id)
  }

  return {
    tour,
    isError,
    isLoading,
    fetchTour,
    updateTour,
    addCity,
    removeCity,
    createDiscount,
    removeDiscount,
  }
})

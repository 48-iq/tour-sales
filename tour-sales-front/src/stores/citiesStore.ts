import axios from 'axios'
import { defineStore } from 'pinia'
import { ref } from 'vue'
import { host } from './host'
import { useAuthStore } from './authStore'

export type City = {
  name: string
  countryName: string
}

export const useCitiesStore = defineStore('cities', () => {
  const cities = ref<City[]>([])
  const isError = ref<boolean>(false)
  const isLoading = ref<boolean>(false)

  const authStore = useAuthStore()

  const fetchCities = async () => {
    isLoading.value = true
    isError.value = false
    try {
      const response = await axios.get<City[]>(`${host}/api/cities/all`)
      if (response.status === 200) {
        cities.value = response.data
      } else {
      }
    } catch (error) {
      if (error instanceof Error) isError.value = true
    }
    isLoading.value = false
  }

  const createCity = async (city: City) => {
    isLoading.value = true
    isError.value = false
    try {
      console.log(city)
      const response = await axios.post<City[]>(`${host}/api/cities/create`, city, {
        headers: { Authorization: `Bearer ${authStore.authData.jwt}` },
      })
      if (response.status === 200) {
        cities.value = response.data
      } else {
        isError.value = true
      }
    } catch (error) {
      if (error instanceof Error) isError.value = true
    }
    isLoading.value = false
  }

  const deleteCity = async (id: string) => {
    isLoading.value = true
    isError.value = false
    try {
      const response = await axios.delete<City[]>(`${host}/api/cities/delete/${id}`, {
        headers: { Authorization: `Bearer ${authStore.authData.jwt}` },
      })
      if (response.status === 200) {
        cities.value = response.data
      } else {
        isError.value = true
      }
    } catch (error) {
      if (error instanceof Error) isError.value = true
    }
    isLoading.value = false
  }

  return { cities, isError, isLoading, fetchCities, createCity, deleteCity }
})

import axios from 'axios'
import { defineStore } from 'pinia'
import { ref } from 'vue'
import { host } from './host'
import { useAuthStore } from './authStore'

export type Country = {
  name: string
}

export const useCountriesStore = defineStore('countries', () => {
  const countries = ref<Country[]>([])
  const isError = ref<boolean>(false)
  const isLoading = ref<boolean>(false)

  const authStore = useAuthStore()

  const fetchCountries = async () => {
    isLoading.value = true
    isError.value = false
    try {
      console.log('fetching countries')
      const response = await axios.get<string[]>(`${host}/api/countries/all`)
      if (response.status === 200) {
        countries.value = response.data.map((country) => ({ name: country }))
      } else {
        isError.value = true
      }
    } catch (error) {
      if (error instanceof Error) isError.value = true
    }
    isLoading.value = false
  }

  const createCountry = async (country: Country) => {
    isLoading.value = true
    isError.value = false
    try {
      const response = await axios.post<string[]>(`${host}/api/countries/create`, null, {
        headers: { Authorization: `Bearer ${authStore.authData.jwt}` },
        params: { name: country.name },
      })
      console.log(response.data)
      if (response.status === 200) {
        countries.value = response.data.map((country) => ({ name: country }))
      } else {
        isError.value = true
      }
    } catch (error) {
      if (error instanceof Error) isError.value = true
    }
    isLoading.value = false
  }

  const deleteCountry = async (id: string) => {
    isLoading.value = true
    isError.value = false
    try {
      const response = await axios.delete<string[]>(`${host}/api/countries/delete/${id}`)
      console.log(response.data)
      if (response.status === 200) {
        countries.value = response.data.map((country) => ({ name: country }))
      } else {
        isError.value = true
      }
    } catch (error) {
      if (error instanceof Error) isError.value = true
    }
    isLoading.value = false
  }

  return { countries, isError, isLoading, fetchCountries, createCountry, deleteCountry }
})

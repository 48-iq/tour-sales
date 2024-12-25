import axios from 'axios'
import { defineStore } from 'pinia'
import { ref } from 'vue'
import { host } from './host'
import { useAuthStore } from './authStore'

export type Company = {
  id: string
  name: string
  email: string
  description: string
}

export const useCompaniesStore = defineStore('companies', () => {
  const companies = ref<Company[]>([])
  const isError = ref<boolean>(false)
  const isLoading = ref<boolean>(false)

  const authStore = useAuthStore()

  const createCompany = async (company: { name: string; description: string; email: string }) => {
    isLoading.value = true
    isError.value = false
    try {
      console.log('create company')
      const response = await axios.post<Company>(`${host}/api/companies/create`, company, {
        headers: { Authorization: `Bearer ${authStore.authData?.jwt}` },
      })
      if (response.status === 200) {
      } else {
        isError.value = true
      }
    } catch (error) {
      if (error instanceof Error) {
        isError.value = true
      }
    }
  }

  const deleteCompany = async (id: string) => {
    isLoading.value = true
    isError.value = false
    try {
      const response = await axios.delete<Company>(`${host}/api/companies/delete/${id}`, {
        headers: { Authorization: `Bearer ${authStore.authData.jwt}` },
      })
      if (response.status === 200) {
        companies.value = companies.value.filter((company) => company.id !== id)
      } else {
        isError.value = true
      }
    } catch (error) {
      if (error instanceof Error) isError.value = true
    }
    isLoading.value = false
  }

  const fetchCompanies = async () => {
    isLoading.value = true
    isError.value = false
    try {
      const response = await axios.get<Company[]>(`${host}/api/companies/all`, {})
      console.log(response.data)
      if (response.status === 200) {
        companies.value = response.data
      } else {
        isError.value = true
      }
    } catch (error) {
      if (error instanceof Error) isError.value = true
    }
    isLoading.value = false
  }

  return { companies, isError, isLoading, fetchCompanies, createCompany, deleteCompany }
})

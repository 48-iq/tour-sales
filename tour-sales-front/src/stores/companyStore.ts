import { defineStore } from 'pinia'
import { ref } from 'vue'
import type { Company } from '@/stores/companiesStore'
import axios from 'axios'
import { host } from './host'
import { useAuthStore } from './authStore'

export const useCompanyStore = defineStore('company', () => {
  const company = ref<Company>({
    id: '',
    description: '',
    email: '',
    name: '',
  })

  const authStore = useAuthStore()

  const isLoading = ref<boolean>(false)
  const isError = ref<boolean>(false)

  const updateCompany = async (data: Company) => {
    isError.value = false
    isLoading.value = true
    try {
      const response = await axios.put<Company>(`${host}/api/companies/update/${data.id}`, data, {
        headers: { Authorization: `Bearer ${authStore.authData.jwt}` },
      })

      if (response.status === 200) {
        company.value = data
      } else {
        isError.value = true
      }
    } catch (error) {
      if (error instanceof Error) isError.value = true
    }
    isLoading.value = false
  }

  const fetchCompany = async (id: string) => {
    isLoading.value = true
    isError.value = false
    try {
      const response = await axios.get<Company>(`${host}/api/companies/${id}`)
      if (response.status === 200) {
        company.value = response.data
      } else {
        isError.value = true
      }
    } catch (error) {
      if (error instanceof Error) isError.value = true
    }
    isLoading.value = false
  }

  return { company, isError, isLoading, updateCompany, fetchCompany }
})

import { ref } from 'vue'
import { useAuthStore } from './authStore'
import axios from 'axios'
import { host } from './host'
import { defineStore } from 'pinia'

export type Contract = {
  id: string
  tourId: string
  userId: string
  price: number
  wasPaid: boolean
}

export const useContractsStore = defineStore('contracts', () => {
  const contracts = ref<Contract[]>([])
  const isError = ref<boolean>(false)
  const isLoading = ref<boolean>(false)

  const authStore = useAuthStore()

  const fetchContracts = async () => {
    isLoading.value = true
    isError.value = false
    try {
      const response = await axios.get<Contract[]>(`${host}/api/contracts/all`, {
        headers: { Authorization: `Bearer ${authStore.authData.jwt}` },
      })
      if (response.status === 200) {
        contracts.value = response.data
      } else {
        isError.value = true
      }
    } catch (error) {
      if (error instanceof Error) isError.value = true
    }
    isLoading.value = false
  }

  const fetchContractsByTourId = async (tourId: string) => {
    isLoading.value = true
    isError.value = false
    try {
      const response = await axios.get<Contract[]>(`${host}/api/contracts/by-tour/${tourId}`, {
        headers: { Authorization: `Bearer ${authStore.authData.jwt}` },
      })
      if (response.status === 200) {
        contracts.value = response.data
      } else {
        isError.value = true
      }
    } catch (error) {
      if (error instanceof Error) isError.value = true
    }
    isLoading.value = false
  }

  const fetchContractsByUserId = async (userId: string) => {
    isLoading.value = true
    isError.value = false
    try {
      const response = await axios.get<Contract[]>(`${host}/api/contracts/by-user/${userId}`, {
        headers: { Authorization: `Bearer ${authStore.authData.jwt}` },
      })
      if (response.status === 200) {
        contracts.value = response.data
      } else {
        isError.value = true
      }
    } catch (error) {
      if (error instanceof Error) isError.value = true
    }
    isLoading.value = false
  }

  const createContract = async (contract: Contract) => {
    isLoading.value = true
    isError.value = false
    try {
      const response = await axios.post<Contract>(`${host}/api/contracts/create`, contract, {
        headers: { Authorization: `Bearer ${authStore.authData.jwt}` },
      })
      if (response.status === 200) {
        contracts.value.push(contract)
      } else {
        isError.value = true
      }
    } catch (error) {
      if (error instanceof Error) isError.value = true
    }
    isLoading.value = false
  }

  const deleteContract = async (id: string) => {
    isLoading.value = true
    isError.value = false
    try {
      const response = await axios.delete<Contract>(`${host}/api/contracts/delete/${id}`, {
        headers: { Authorization: `Bearer ${authStore.authData.jwt}` },
      })
      if (response.status === 200) {
        contracts.value = contracts.value.filter((contract) => contract.id !== id)
      } else {
        isError.value = true
      }
    } catch (error) {
      if (error instanceof Error) isError.value = true
    }
    isLoading.value = false
  }

  return {
    contracts,
    isError,
    isLoading,
    fetchContracts,
    fetchContractsByTourId,
    fetchContractsByUserId,
    createContract,
    deleteContract,
  }
})

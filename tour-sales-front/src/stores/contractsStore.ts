import { ref } from 'vue'
import { useAuthStore } from './authStore'
import axios from 'axios'
import { host } from './host'
import { defineStore } from 'pinia'
import type { User } from './userStore'

export type Contract = {
  id: string
  tourId: string
  userId: string
  price: number
}

export const useContractsStore = defineStore('contracts', () => {
  const contracts = ref<Contract[]>([])
  const isError = ref<boolean>(false)
  const isLoading = ref<boolean>(false)
  const users = ref<User[]>([])

  const authStore = useAuthStore()

  const fetchUsers = async () => {
    isLoading.value = true
    isError.value = false
    try {
      const response = await axios.get<User[]>(`${host}/api/users/all`, {})
      if (response.status === 200) {
        users.value = response.data
      } else {
        isError.value = true
      }
    } catch (error) {
      if (error instanceof Error) isError.value = true
    }
    isLoading.value = false
  }

  const fetchContracts = async () => {
    isLoading.value = true
    isError.value = false
    try {
      const response = await axios.get<Contract[]>(`${host}/api/contracts/all`)
      if (response.status === 200) {
        contracts.value = response.data
      } else {
        isError.value = true
      }
    } catch (error) {
      if (error instanceof Error) isError.value = true
    }
    console.log(contracts.value)
    isLoading.value = false
  }

  const createContract = async (contract: { tourId: string; userId: string }) => {
    isLoading.value = true
    isError.value = false
    try {
      const response = await axios.post<Contract>(`${host}/api/contracts/create`, contract, {
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
    fetchContracts()
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
    fetchContracts()
  }

  return {
    contracts,
    isError,
    isLoading,
    fetchContracts,
    createContract,
    deleteContract,
    users,
    fetchUsers,
  }
})

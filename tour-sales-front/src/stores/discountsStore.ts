import { ref } from 'vue'
import { useAuthStore } from './authStore'
import { host } from './host'
import axios from 'axios'
import { defineStore } from 'pinia'

export type Discount = {
  tourId: string
  categoryName: string
  discount: number
  tourTitle: string
}

export const useDiscountsSore = defineStore('discounts', () => {
  const discounts = ref<Discount[]>([])
  const isError = ref<boolean>(false)
  const isLoading = ref<boolean>(false)

  const authStore = useAuthStore()

  const fetchDiscounts = async () => {
    isLoading.value = true
    isError.value = false
    try {
      const response = await axios.get<Discount[]>(`${host}/api/discounts/all`, {
        headers: { Authorization: `Bearer ${authStore.authData.jwt}` },
      })
      if (response.status === 200) {
        console.log(response.data)
        discounts.value = response.data
      } else {
        isError.value = true
      }
    } catch (error) {
      if (error instanceof Error) isError.value = true
    }
    isLoading.value = false
  }

  const createDiscount = async (discount: Discount) => {
    isLoading.value = true
    isError.value = false
    try {
      const response = await axios.post<Discount>(`${host}/api/discounts/create`, discount, {
        headers: { Authorization: `Bearer ${authStore.authData.jwt}` },
      })
      if (response.status === 200) {
        console.log(response.data)
        discounts.value.push(discount)
      } else {
        isError.value = true
      }
    } catch (error) {
      if (error instanceof Error) isError.value = true
    }
    isLoading.value = false
  }

  const fetchDiscountsByTourId = async (tourId: string) => {
    isLoading.value = true
    isError.value = false
    try {
      const response = await axios.get<Discount[]>(`${host}/api/discounts/by-tour/${tourId}`, {
        headers: { Authorization: `Bearer ${authStore.authData.jwt}` },
      })
      if (response.status === 200) {
        console.log(response.data)
        discounts.value = response.data
      } else {
        isError.value = true
      }
    } catch (error) {
      if (error instanceof Error) isError.value = true
    }
    isLoading.value = false
  }

  const deleteDiscount = async (categoryName: string, tourId: string) => {
    isLoading.value = true
    isError.value = false
    try {
      const response = await axios.delete<Discount>(
        `${host}/api/discounts/delete/${categoryName}/${tourId}`,
        {
          headers: { Authorization: `Bearer ${authStore.authData.jwt}` },
        },
      )
      if (response.status === 200) {
        discounts.value = discounts.value.filter(
          (discount) => discount.tourId !== tourId && discount.categoryName !== categoryName,
        )
        console.log(discounts.value)
      } else {
        isError.value = true
      }
    } catch (error) {
      if (error instanceof Error) isError.value = true
    }
    isLoading.value = false
  }

  return {
    discounts,
    isError,
    isLoading,
    fetchDiscounts,
    createDiscount,
    deleteDiscount,
    fetchDiscountsByTourId,
  }
})

import axios from 'axios'
import { defineStore } from 'pinia'
import { ref } from 'vue'
import { host } from './host'
import { jwtDecode } from 'jwt-decode'
import Cookies from 'cookies-ts'

const cookies = new Cookies()

export type JwtPayload = {
  username: string
  id: string
  roles: string[]
  exp: number
}

export type authData = {
  username: string
  id: string
  roles: string[]
  jwt: string
}

export type loginData = {
  username: string
  password: string
}

export type registerData = {
  username: string
  password: string
}

export const useAuthStore = defineStore('auth', () => {
  const authData = ref<authData | null>(null)
  const isAuth = ref<boolean>(false)
  const isLoading = ref<boolean>(false)
  const isError = ref<boolean>(false)

  const loadCookies = () => {
    const jwt = cookies.get('jwt')
    if (!jwt) return
    authData.value = parseJwt(jwt)
    isAuth.value = true
  }

  const removeCookies = () => {
    cookies.remove('jwt')
  }

  const logout = () => {
    removeCookies()
    authData.value = null
    isAuth.value = false
  }

  const login = async (data: loginData) => {
    isLoading.value = true
    isError.value = false
    const response = await axios.post<string>(`${host}/api/auth/login`, data)
    if (response.status === 200) {
      const jwt = response.data
      authData.value = parseJwt(jwt)
      saveCookies()
      isAuth.value = true
    } else {
      isError.value = true
    }
    isLoading.value = false
  }

  const register = async (data: registerData) => {
    isLoading.value = true
    isError.value = false
    const response = await axios.post<string>(`${host}/api/auth/register`, data)
    if (response.status === 200) {
      const jwt = response.data
      authData.value = parseJwt(jwt)
      saveCookies()
      isAuth.value = true
    } else {
      isError.value = true
    }
    isLoading.value = false
  }

  const parseJwt = (jwt: string) => {
    const payload = jwtDecode<JwtPayload>(jwt)
    return {
      username: payload.username,
      id: payload.id,
      roles: payload.roles,
      jwt: jwt,
    }
  }

  const saveCookies = () => {
    if (!authData.value) return

    const exp = jwtDecode<JwtPayload>(authData.value.jwt).exp
    cookies.set('jwt', authData.value.jwt, {
      expires: exp,
    })
  }

  return { authData, isAuth, isLoading, login, loadCookies, isError, register, logout }
})

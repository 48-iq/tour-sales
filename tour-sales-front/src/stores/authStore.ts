import axios from 'axios'
import { defineStore } from 'pinia'
import { ref } from 'vue'
import { host } from './host'
import { jwtDecode } from 'jwt-decode'
import Cookies from 'cookies-ts'
import { useRouter } from 'vue-router'

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
  const authData = ref<authData>({
    username: '',
    id: '',
    roles: [],
    jwt: '',
  })
  const isAuth = ref<boolean>(false)
  const isLoginLoading = ref<boolean>(false)
  const isLoginError = ref<boolean>(false)
  const isRegisterLoading = ref<boolean>(false)
  const isRegisterError = ref<boolean>(false)
  const isAdminPassError = ref<boolean>(false)

  const router = useRouter()

  const isAdmin = () => {
    return authData.value ? authData.value.roles.includes('ROLE_ADMIN') : false
  }

  const loadCookies = () => {
    const jwt = cookies.get('jwt')
    if (!jwt) return
    authData.value = parseJwt(jwt)
    isAuth.value = true
  }

  const removeCookies = () => {
    cookies.remove('jwt')
  }

  const becomeAdmin = async (adminPassword: string) => {
    try {
      isAdminPassError.value = false
      const response = await axios.post<string>(
        `${host}/api/auth/become-admin`,
        { adminPassword },
        {
          headers: {
            Authorization: `Bearer ${authData.value.jwt}`,
          },
        },
      )
      if (response.status === 200) {
        const jwt = response.data
        authData.value = parseJwt(jwt)
        saveCookies()
        isAuth.value = true
      } else {
        isAdminPassError.value = true
      }
    } catch (error) {
      if (error instanceof Error) isAdminPassError.value = true
    }
  }

  const logout = () => {
    removeCookies()
    authData.value = { username: '', id: '', roles: [], jwt: '' }
    isAuth.value = false
    router.push({ name: 'login' })
  }

  const login = async (data: loginData) => {
    try {
      isLoginLoading.value = true
      isLoginError.value = false
      const response = await axios.post<string>(`${host}/api/auth/login`, data)
      if (response.status === 200) {
        const jwt = response.data
        authData.value = parseJwt(jwt)
        saveCookies()
        isAuth.value = true
        router.push({ name: 'home' })
      } else {
        isLoginError.value = true
      }
    } catch (error) {
      if (error instanceof Error) isLoginError.value = true
    }
    isLoginLoading.value = false
  }

  const register = async (data: registerData) => {
    try {
      isRegisterLoading.value = true
      isRegisterError.value = false
      const response = await axios.post<string>(`${host}/api/auth/register`, data)
      if (response.status === 200) {
        const jwt = response.data
        authData.value = parseJwt(jwt)
        saveCookies()
        isAuth.value = true
        router.push({ name: 'home' })
      } else {
        isRegisterError.value = true
      }
    } catch (error) {
      if (error instanceof Error) isRegisterError.value = true
    }

    isRegisterLoading.value = false
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

  return {
    authData,
    isAuth,
    isRegisterLoading,
    isLoginLoading,
    login,
    loadCookies,
    isRegisterError,
    isLoginError,
    register,
    logout,
    isAdmin,
    becomeAdmin,
    isAdminPassError,
  }
})

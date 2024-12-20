import axios from "axios";
import { defineStore } from "pinia";
import { ref } from "vue";
import { host } from "./host";
import { jwtDecode } from "jwt-decode";


type JwtPayload = {
  username: string,
  roles: string[],
  id: string
}

export const authStore = defineStore("auth", () => {
  const isAuth = ref(false)

  const username = ref("")

  const roles = ref([""])

  const id = ref("")

  function login(pUsername: string, pPassword: string) {
    doLogin(pUsername, pPassword).then((response) => {
      isAuth.value = true
      const jwt = response.data
      const payload = jwtDecode<JwtPayload>(jwt)
      username.value = payload.username
      roles.value = payload.roles
      id.value = payload.id
    })
  }

  function register(pUsername: string, pPassword: string) {
    doRegister(pUsername, pPassword).then((response) => {
      isAuth.value = true
      const jwt = response.data
      const payload = jwtDecode<JwtPayload>(jwt)
      username.value = payload.username
      roles.value = payload.roles
      id.value = payload.id
    })
  }

  return { isAuth, username, roles, id, login, register};
})

async function doLogin(username: string, password: string) {
  return axios.post<string>(host + "/api/auth/login", {
    username,
    password
  })
}

async function doRegister(username: string, password: string) {
  return axios.post<string>(host + "/api/auth/register", {
    username,
    password
  })
}

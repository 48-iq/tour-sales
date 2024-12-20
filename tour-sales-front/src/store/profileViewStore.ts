import axios from "axios";
import { defineStore } from "pinia";
import { ref } from "vue";
import { host } from "./host";

type User = {
  id: string
  username: string;
  email: string;
  birthDate: string,
  name: string,
  surname: string,
  phone: string
}

type UserCategory = {
  
}

export const profileViewStore = defineStore("profileView", () => {
  const user = ref<User>({
    id: "",
    username: "",
    email: "",
    birthDate: "",
    name: "",
    surname: "",
    phone: ""
  });

  const categories = ref<string[]>([]);


  return { user, categories };


});


async function fetchUser(id: string) {
  return axios.get<User>(host + "/api/user/" + id);
}

async function fetchCategories(id: string) {
  return axios.get<string[]>(host + "/api/user-category/by-user/" + id);
}

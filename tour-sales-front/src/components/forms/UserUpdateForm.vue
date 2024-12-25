<script lang="ts" setup>
import BaseInput from '../inputs/BaseInput.vue'
import { useUserUpdateStore } from '@/stores/userUpdateStore'
import BaseButton from '../buttons/BaseButton.vue'
import { ref } from 'vue'
import { useAuthStore } from '@/stores/authStore'

const userUpdateStore = useUserUpdateStore()
const authStore = useAuthStore()

const submit = (e: Event) => {
  e.preventDefault()
  userUpdateStore.saveUser(userUpdateStore.user)
}

const deleteUser = (e: Event) => {
  e.preventDefault()
  userUpdateStore.deleteUser(userUpdateStore.user.id)
}

const adminPass = ref('')

const becomeAdmin = (e: Event) => {
  e.preventDefault()
  authStore.becomeAdmin(adminPass.value)
}
</script>

<template>
  <form class="form" @submit="submit">
    <label>Имя:</label>
    <BaseInput v-model="userUpdateStore.user.name" />
    <label>Фамилия:</label>
    <BaseInput v-model="userUpdateStore.user.surname" />
    <label>Почта:</label>
    <BaseInput v-model="userUpdateStore.user.email" />
    <label>Телефон:</label>
    <BaseInput v-model="userUpdateStore.user.phone" />
    <label>Дата рождения:</label>
    <BaseInput v-model="userUpdateStore.user.birthDate" />
    <BaseButton @click="deleteUser">Удалить</BaseButton>
    <BaseButton>Сохранить</BaseButton>
    <label></label>
    <label></label>
    <BaseButton @click="becomeAdmin">Стать админом</BaseButton>
    <BaseInput type="password" v-model="adminPass" />
  </form>
</template>

<style scoped>
.form {
  width: 100%;
  background-color: var(--color-green);
  display: grid;
  grid-template-columns: 1fr 1fr;
  font-size: 1.5rem;
  padding: 20px;
  border-radius: 10px;
  gap: 10px;
  text-align: right;
}
</style>

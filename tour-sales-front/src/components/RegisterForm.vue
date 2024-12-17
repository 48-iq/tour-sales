<script lang="ts" setup>

import { reactive } from 'vue';
import StandardInput from './StandardInput.vue';
import StandardButton from './StandardButton.vue';

  const registerFormData = reactive({
    username: '',
    password: ''
  })
</script>

<template>

  <form class="register-form">
    <h1>Форма входа</h1>
    <label for="username">Логин</label>
    <StandardInput v-model="registerFormData.username" id="username"/>

    <label for="password">Пароль</label>
    <StandardInput v-model="registerFormData.password" id="password"  type="password"/>

    <StandardButton>Зарегистрироваться</StandardButton>
    <RouterLink to="/login">Войти</RouterLink>
  </form>
</template>

<style scoped>
  .register-form {
    width: 400px;
    padding: 15px;
    display: flex;
    flex-direction: column;
    gap: 15px;
    background-color: var(--color-white);
  }
</style>

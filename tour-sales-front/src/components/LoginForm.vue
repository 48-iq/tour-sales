<script lang="ts" setup>

import { reactive } from 'vue';
import StandardInput from './StandardInput.vue';
import StandardButton from './StandardButton.vue';

  const loginFormData = reactive({
    username: '',
    password: ''
  })
</script>

<template>

  <form class="login-form">
    <h1>Форма входа</h1>
    <label for="username">Логин</label>
    <StandardInput v-model="loginFormData.username" id="username"/>

    <label for="password">Пароль</label>
    <StandardInput v-model="loginFormData.password" id="password" type="password"/>

    <StandardButton>Войти</StandardButton>
    <RouterLink to="/register">Зарегистрироваться</RouterLink>
  </form>
</template>

<style scoped>
  .login-form {
    width: 400px;
    padding: 15px;
    display: flex;
    flex-direction: column;
    gap: 15px;
    background-color: var(--color-white);
  }
</style>

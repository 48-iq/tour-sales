<script setup lang="ts">
import { reactive } from 'vue';

  const user = reactive({
    id: '111111-1111111-1111111',
    username: 'johndoe',
    name: 'John',
    surname: 'Doe',
    email: 'VYy3S@example.com',
    birthDate: '1990-01-01',
    phone: '+380500000000'

  })

</script>
<template>

  <div class="view">
    <h1>Профиль</h1>
    <div class="profile-data">
      <p>id: {{ user.id }}</p>
      <p>Логин: {{ user.username }}</p>
      <p>Имя: {{ user.name }}</p>
      <p>Фамилия: {{ user.surname }}</p>
      <p>email: {{ user.email }}</p>
      <p>дата рождения: {{ user.birthDate }}</p>
      <p>телефон: {{ user.phone }}</p>
    </div>
    <RouterLink to="/home/profile-edit">Редактировать</RouterLink>
  </div>
</template>

<style scoped>
  .view {
    display: flex;
    flex-direction: column;
    gap: 10px;
    align-items: center;
  }

  .profile-data {
    margin-top: 20px;
    display: flex;
    flex-direction: column;
    align-items: left;
    gap: 10px;
  }
</style>

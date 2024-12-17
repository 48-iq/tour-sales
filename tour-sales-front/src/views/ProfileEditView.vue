<script setup lang="ts">
import StandardButton from '@/components/StandardButton.vue';
import { reactive } from 'vue';
import StandardInput from '@/components/StandardInput.vue';

  const profileEditFormData = reactive({
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
    <h1>Редактировать профиль</h1>
    <form>

      <label for="name">Имя</label>
      <StandardInput v-model="profileEditFormData.name" id="name"/>

      <label for="surname">Фамилия</label>
      <StandardInput v-model="profileEditFormData.surname" id="surname"/>

      <label for="email">email</label>
      <StandardInput v-model="profileEditFormData.email" id="email"/>

      <label for="birthDate">дата рождения</label>
      <StandardInput v-model="profileEditFormData.birthDate" id="birthDate"/>

      <label for="phone">телефон</label>
      <StandardInput v-model="profileEditFormData.phone" id="phone"/>

      <StandardButton>Сохранить</StandardButton>
    </form>
  </div>
</template>

<style scoped>
  .view {
    display: flex;
    flex-direction: column;
    gap: 10px;
    align-items: center;
  }
  form {
    width: 400px;
    display: flex;
    flex-direction: column;
    gap: 10px
  }

</style>

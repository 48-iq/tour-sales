<script lang="ts" setup></script>

<template>
  <div class="profile">
    <label>Название:</label>
    <span>{{ props.user.name }}</span>
    <label>Описание:</label>
    <span>{{ props.user.surname }}</span>
    <label>Цена:</label>
    <span>{{ props.user.email }}</span>
    <label>Телефон:</label>
    <span>{{ props.user.phone }}</span>
    <label>Дата рождения:</label>
    <span>{{ props.user.birthDate }}</span>
  </div>
</template>

<style scoped>
.profile {
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

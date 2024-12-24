<script lang="ts" setup>
import BaseInput from '@/components/inputs/BaseInput.vue'
import BaseButton from '@/components/buttons/BaseButton.vue'
</script>

<template>
  <form class="form">
    <div class="inputs">
      <label>Логин:</label>
      <BaseInput />
      <label>Пароль:</label>
      <BaseInput type="password" />
    </div>
    <div class="buttons">
      <BaseButton>Войти</BaseButton>
    </div>
    <div class="links">
      <label>Нет аккаунта?</label>
      <RouterLink to="/auth/register">Зарегистрироваться</RouterLink>
    </div>
  </form>
</template>

<style scoped>
.form {
  background-color: var(--color-green);
  width: 350px;
  height: 250px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: center;
  padding: 25px;
  border-radius: 15px;
}
.inputs {
  display: grid;
  grid-template-columns: 60px auto;
  row-gap: 20px;
  column-gap: 10px;
  align-items: center;
  align-content: center;
  text-align: right;
}
.buttons {
  display: grid;
  grid-template-columns: 1fr 1fr;
  column-gap: 30px;
  width: 100%;
}
.links {
  display: grid;
  grid-template-columns: 1fr 1fr;
  font-size: 15px;
  column-gap: 30px;
  width: 100%;
}
</style>

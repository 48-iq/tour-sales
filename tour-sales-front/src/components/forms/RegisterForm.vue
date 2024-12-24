<script lang="ts" setup>
import BaseInput from '@/components/inputs/BaseInput.vue'
import BaseButton from '@/components/buttons/BaseButton.vue'
import { useAuthStore } from '@/stores/authStore';
import { ref } from 'vue';
import BaseLoader from '../loaders/BaseLoader.vue';

const authStore = useAuthStore();

const registerData = ref({
  username: '',
  password: ''
})

const submit = (e: Event) => {
  e.preventDefault();
  authStore.register(registerData.value);
}
</script>

<template>
  <form class="form" @submit="submit">
    <div class="inputs">
      <label>Логин:</label>
      <BaseInput v-model="registerData.username"/>
      <label>Пароль:</label>
      <BaseInput type="password" v-model="registerData.password"/>
    </div>
    <div class="buttons">
      <BaseButton>Зарегистрироваться</BaseButton>
      <BaseLoader v-if="authStore.isRegisterLoading"/>
      <span v-else-if="authStore.isRegisterError">ошибка</span>
    </div>
    <div class="links">
      <label>Есть аккаунт?</label>
      <RouterLink to="/auth/login">Войти</RouterLink>
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
  column-gap: 10px;
  width: 100%;
  align-items: center;

}
.links {
  display: grid;
  grid-template-columns: 1fr 1fr;
  font-size: 15px;
  column-gap: 30px;
  width: 100%;
}
</style>

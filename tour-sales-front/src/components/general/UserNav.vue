<script lang="ts" setup>
import { useAuthStore } from '@/stores/authStore'
import { RouterLink } from 'vue-router'

const authStore = useAuthStore()
</script>

<template>
  <nav class="nav">
    <RouterLink :to="'/home'"><div class="link top-link">Главная</div></RouterLink>
    <RouterLink :to="`/users/${authStore.authData.id}`"><div class="link">Профиль</div></RouterLink>
    <RouterLink :to="'/tours'"><div class="link">Туры</div></RouterLink>
    <RouterLink :to="'/companies'"><div class="link">Компании</div></RouterLink>
    <RouterLink :to="'/Cities'"><div class="link">Города</div></RouterLink>
    <RouterLink :to="'/Countries'"><div class="link bottom-link">Страны</div></RouterLink>
  </nav>
</template>

<style scoped>
.nav {
  display: flex;
  flex-direction: column;
  background-color: var(--color-dark-green);
  width: 175px;
  border-radius: 10px;
}
.link {
  padding: 10px;
  text-align: center;
  border: solid 1px var(--color-green);
}
.link:hover {
  background-color: var(--color-green);
}
.top-link {
  border-top-left-radius: 10px;
  border-top-right-radius: 10px;
}
.bottom-link {
  border-bottom-left-radius: 10px;
  border-bottom-right-radius: 10px;
}
</style>

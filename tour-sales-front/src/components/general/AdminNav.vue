<script lang="ts" setup>
import { RouterLink } from 'vue-router'
</script>

<template>
  <nav class="nav">
    <RouterLink to="/create-tour"><div class="link top-link">Добавить тур</div></RouterLink>
    <RouterLink to="/tours"><div class="link">Добавить скидку</div></RouterLink>
    <RouterLink to="/categories"><div class="link">Категории</div></RouterLink>
    <RouterLink to="/create-company"><div class="link">Добавить компанию</div></RouterLink>
    <RouterLink to="/countries"><div class="link">Страны</div></RouterLink>
    <RouterLink to="/cities"><div class="link bottom-link">Города</div></RouterLink>
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

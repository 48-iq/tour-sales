<script setup lang="ts">
import StandardButton from '@/components/StandardButton.vue';

</script>
<template>
  <div class="page">
    <nav>
      <StandardButton>Профиль</StandardButton>
      <StandardButton>Туры</StandardButton>
      <StandardButton>Мои контракты</StandardButton>
      <StandardButton>Скидки</StandardButton>
      <StandardButton>Компании</StandardButton>
      <RouterLink to="/login">Выйти</RouterLink>
    </nav>
    <main>
      <RouterView/>
    </main>
  </div>
</template>

<style scoped>
  .page {
    display: flex;
    padding: 5px;
  }
  main {
    padding: 5px;
    width: 100%;
  }
  nav {

    padding: 5px;
    display: flex;
    flex-direction: column;
    gap: 5px;
    width: 200px;
    height: fit-content;
    border: solid 2px var(--color-light-green);
  }

</style>

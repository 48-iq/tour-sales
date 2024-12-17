<script setup lang="ts">
  const model = defineModel<string>()
</script>

<template>
  <input v-model="model" class="standard-input"/>
</template>

<style scoped>
  .standard-input {
    background-color: var(--color-white);
    border: 2px solid var(--color-light-green);
    width: 100%;
    height: 30px;
    padding-left: 10px;
  }
</style>

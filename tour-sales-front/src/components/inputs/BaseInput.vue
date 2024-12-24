<script lang="ts" setup>
  const model = defineModel<string|number>();
</script>

<template>
  <input class="input" v-model="model"/>
</template>

<style scoped>
.input {
  font-size: 1.3rem;
  background-color: var(--color-dark-green);
  width: 100%;
  border-radius: 5px;
  border: none;
  padding: 5px;
  padding-left: 8px;
}
</style>

<script lang="ts" setup>
const props = defineProps<{ title: string; description: string }>()
</script>

<template>
  <div class="element">
    <h3>{{ props.title }}</h3>
    <p>{{ props.description }}</p>
  </div>
</template>

<style scoped>
.element {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 10px;
  width: 100%;
  padding: 10px;
  background-color: var(--color-green);
  border: solid 1px var(--color-dark-green);
  border-radius: 10px;
}
</style>

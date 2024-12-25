<script lang="ts" setup>
import UserCategory from '@/components/user/UserCategory.vue'
const props = defineProps<{ categories: { title: string; description: string }[] }>()
</script>

<template>
  <ul class="list">
    <li
      v-for="category in props.categories"
      :key="category.title"
      :style="{ listStyleType: 'none' }"
    >
      <UserCategory :title="category.title" :description="category.description" />
    </li>
  </ul>
</template>

<style scoped>
.list {
  display: flex;
  min-height: 100px;
  width: 100%;
  flex-direction: column;
  gap: 10px;
  padding: 10px;
  border: solid 3px var(--color-green);
  border-radius: 10px;
}
</style>

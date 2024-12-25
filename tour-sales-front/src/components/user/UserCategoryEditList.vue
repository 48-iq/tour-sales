<script lang="ts" setup>
import UserCategory from '@/components/user/UserCategory.vue'
import BaseButton from '../buttons/BaseButton.vue'
import { useUserUpdateStore } from '@/stores/userUpdateStore'
const props = defineProps<{ categories: { title: string; description: string }[] }>()

const userUpdateStore = useUserUpdateStore()
</script>

<template>
  <ul class="list">
    <li
      v-for="category in props.categories"
      :key="category.title"
      :style="{ listStyleType: 'none', display: 'grid', gridTemplateColumns: '4fr 1fr' }"
    >
      <UserCategory :title="category.title" :description="category.description" />
      <BaseButton @click="userUpdateStore.deleteCategory(category.title)">Удалить</BaseButton>
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

<script setup lang="ts">
import { useUserCategoriesStore } from '@/stores/userCategoriesStore'
import { useUserUpdateStore } from '@/stores/userUpdateStore'
import { ref } from 'vue'

const userCategoriesStore = useUserCategoriesStore()
userCategoriesStore.fetchUserCategories()
const userUpdateStore = useUserUpdateStore()
const selectedCategory = ref('')

const addCategory = () => {
  console.log(selectedCategory.value)
  userUpdateStore.addCategory(selectedCategory.value)
  selectedCategory.value = ''
}
</script>

<template>
  <div class="container">
    <span class="element">Добавить категорию:</span>
    <select v-model="selectedCategory" class="element" @change="addCategory">
      <option
        v-for="category in userCategoriesStore.userCategories.filter(
          (category) => !userUpdateStore.user.categories.includes(category),
        )"
        :key="category.title"
        class="element"
      >
        {{ category.title }}
      </option>
    </select>
  </div>
</template>

<style scoped>
.container {
  display: grid;
  grid-template-columns: 1fr 3fr;
  width: 100%;
  height: 40px;
  gap: 10px;
  text-align: right;
  align-items: center;
}
.element {
  text-align: center;
  cursor: pointer;
  height: 30px;
  border-radius: 10px;
  background-color: var(--color-green);
}
</style>

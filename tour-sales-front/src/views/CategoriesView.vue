<template>
  <div class="user-categories">
    <!-- Форма для добавления категории -->
    <div class="add-category-form">
      <input v-model="newCategory.title" placeholder="Введите название категории" />
      <textarea
        v-model="newCategory.description"
        placeholder="Введите описание категории"
      ></textarea>
      <button @click="addCategory" :disabled="store.isLoading">Добавить категорию</button>
    </div>

    <!-- Сообщение об ошибке -->
    <div v-if="store.isError" class="error-message">
      Произошла ошибка при загрузке данных или добавлении категории.
    </div>

    <!-- Список категорий -->
    <div v-if="!store.isLoading && store.userCategories.length > 0" class="category-list">
      <div v-for="category in store.userCategories" :key="category.title" class="category-item">
        <div class="category-info">
          <h3>{{ category.title }}</h3>
          <p>{{ category.description }}</p>
        </div>
        <button @click="deleteCategory(category.title)" :disabled="store.isLoading">Удалить</button>
      </div>
    </div>

    <!-- Загрузка данных -->
    <div v-if="store.isLoading" class="loading-message">Загрузка...</div>
  </div>
</template>

<script lang="ts" setup>
import { ref, onMounted } from 'vue'
import { useUserCategoriesStore } from '@/stores/userCategoriesStore'

// Ссылка на store для работы с категориями
const store = useUserCategoriesStore()

// Локальные данные для новой категории
const newCategory = ref({
  title: '',
  description: '',
})

// Функция для добавления новой категории
const addCategory = async () => {
  if (newCategory.value.title.trim() && newCategory.value.description.trim()) {
    store.createUserCategory(newCategory.value)
    newCategory.value.title = ''
    newCategory.value.description = ''
  }
}

// Функция для удаления категории
const deleteCategory = async (title: string) => {
  await store.deleteUserCategory(title)
}

// Загружаем категории при монтировании компонента
onMounted(() => {
  store.fetchUserCategories()
})

// Возвращаем данные и методы для использования в шаблоне
</script>

<style scoped>
.user-categories {
  width: 400px;
  margin: 0 auto;
}

.add-category-form {
  display: flex;
  flex-direction: column;
  gap: 10px;
}

.add-category-form input,
.add-category-form textarea {
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  resize: none;
  background-color: var(--color-dark-green);
}

.add-category-form button {
  padding: 10px;
  background-color: var(--color-green);
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

.add-category-form button:disabled {
  background-color: #cccccc;
}

.error-message {
  color: var(--color-light-brown);
  margin-top: 20px;
}

.category-list {
  margin-top: 20px;
}

.category-item {
  display: flex;
  justify-content: space-between;
  padding: 10px;
  border: 1px solid #ddd;
  margin-bottom: 10px;
  border-radius: 5px;
}

.category-item button {
  background-color: var(--color-light-brown);
  color: white;
  border: none;
  padding: 5px 10px;
  cursor: pointer;
}

.category-item button:disabled {
  background-color: #cccccc;
}

.loading-message {
  text-align: center;
  margin-top: 20px;
}
</style>

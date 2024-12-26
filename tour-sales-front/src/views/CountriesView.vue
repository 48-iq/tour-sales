<template>
  <div class="countries">
    <!-- Форма для добавления страны -->
    <div v-if="authStore.isAdmin()" class="add-country-form">
      <input v-model="newCountry.name" placeholder="Введите название страны" />
      <button @click="addCountry" :disabled="store.isLoading">Добавить страну</button>
    </div>

    <!-- Сообщение об ошибке -->
    <div v-if="store.isError" class="error-message">
      Произошла ошибка при загрузке данных или добавлении страны.
    </div>

    <!-- Список стран -->
    <div v-if="!store.isLoading && store.countries.length > 0" class="country-list">
      <div v-for="country in store.countries" :key="country.name" class="country-item">
        <div class="country-info">
          <h3>{{ country.name }}</h3>
        </div>
        <button @click="deleteCountry(country.name)" :disabled="store.isLoading">Удалить</button>
      </div>
    </div>

    <!-- Загрузка данных -->
    <div v-if="store.isLoading" class="loading-message">Загрузка...</div>
  </div>
</template>

<script lang="ts" setup>
import { ref, onMounted } from 'vue'
import { useCountriesStore } from '@/stores/countriesStore'
import { useAuthStore } from '@/stores/authStore'

// Подключаем Pinia store для работы с данными стран
const store = useCountriesStore()

const authStore = useAuthStore()

// Локальное состояние для новой страны
const newCountry = ref({
  name: '',
})

// Функция для добавления страны
const addCountry = () => {
  if (newCountry.value.name.trim()) {
    store.createCountry(newCountry.value)
    newCountry.value.name = '' // Очищаем поле после добавления
  }
}

// Функция для удаления страны
const deleteCountry = async (name: string) => {
  store.deleteCountry(name)
}

// Загружаем список стран при монтировании компонента
onMounted(() => {
  store.fetchCountries()
})

// Подключаем данные из Pinia store
</script>

<style scoped>
.countries {
  width: 400px;
  margin: 0 auto;
}

.add-country-form {
  display: flex;
  flex-direction: column;
  gap: 10px;
}

.add-country-form input {
  padding: 10px;
  border-radius: 5px;
  background-color: var(--color-dark-green);
}

.add-country-form button {
  padding: 10px;
  background-color: var(--color-green);
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

.add-country-form button:disabled {
  background-color: #cccccc;
}

.error-message {
  color: var(--color-light-brown);
  margin-top: 20px;
}

.country-list {
  margin-top: 20px;
}

.country-item {
  display: flex;
  justify-content: space-between;
  padding: 10px;
  border: 1px solid #ddd;
  margin-bottom: 10px;
  border-radius: 5px;
}

.country-item button {
  background-color: var(--color-light-brown);
  color: white;
  border: none;
  padding: 5px 10px;
  cursor: pointer;
}

.country-item button:disabled {
  background-color: #cccccc;
}

.loading-message {
  text-align: center;
  margin-top: 20px;
}
</style>

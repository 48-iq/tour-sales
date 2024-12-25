<template>
  <div class="edit-tour">
    <h2>Редактирование тура</h2>

    <!-- Сообщение об ошибке -->
    <div v-if="tourStore.isError" class="error-message">
      Произошла ошибка при загрузке или обновлении тура.
    </div>

    <!-- Форма редактирования -->
    <form @submit.prevent="handleSubmit" class="tour-form">
      <div class="form-group">
        <label for="title">Название тура</label>
        <input v-model="tourStore.tour.title" id="title" type="text" required />
      </div>

      <div class="form-group">
        <label for="description">Описание</label>
        <textarea v-model="tourStore.tour.description" id="description" required></textarea>
      </div>

      <div class="form-group">
        <label for="price">Цена</label>
        <input v-model="tourStore.tour.price" id="price" type="number" min="0" required />
      </div>

      <div class="form-group">
        <label for="startAt">Дата начала</label>
        <input v-model="tourStore.tour.startAt" id="startAt" type="date" required />
      </div>

      <div class="form-group">
        <label for="finishAt">Дата окончания</label>
        <input v-model="tourStore.tour.finishAt" id="finishAt" type="date" required />
      </div>

      <button type="submit" :disabled="tourStore.isLoading">Сохранить</button>
    </form>

    <h3>Города тура</h3>

    <!-- Список городов -->
    <div v-if="tourStore.tour.cities.length > 0">
      <ul>
        <li v-for="(city, index) in tourStore.tour.cities" :key="city.name">
          {{ city.name }}
          <button @click="removeCity(index)">Удалить</button>
        </li>
      </ul>
    </div>
    <div v-else>
      <p>Города не добавлены</p>
    </div>

    <!-- Выпадающий список для добавления города -->
    <div>
      <label for="add-city">Добавить город</label>
      <select v-model="selectedCityId" id="add-city">
        <option v-for="city in citiesStore.cities" :key="city.name" :value="city.name">
          {{ city.name }}
        </option>
      </select>
      <button @click="addCity" :disabled="!selectedCityId || tourStore.isLoading">Добавить</button>
    </div>

    <div v-if="tourStore.isLoading" class="loading-message">Сохранение...</div>
  </div>
</template>

<script lang="ts" setup>
import { ref, onMounted } from 'vue'
import { useTourStore } from '@/stores/tourStore'
import { useCitiesStore } from '@/stores/citiesStore'

const props = defineProps<{ id: string }>()

// Состояния компонента
const tourStore = useTourStore()
const citiesStore = useCitiesStore()

// Стейт для выбора города
const selectedCityId = ref<string | null>(null)

// Загружаем тур по переданному id
onMounted(() => {
  tourStore.fetchTour(props.id)
  citiesStore.fetchCities() // Загружаем все города
})

// Функция для обновления информации о туре
const handleSubmit = async () => {
  if (
    tourStore.tour.title &&
    tourStore.tour.description &&
    tourStore.tour.price &&
    tourStore.tour.startAt &&
    tourStore.tour.finishAt
  ) {
    tourStore.updateTour(tourStore.tour)
  }
}

// Функция для удаления города из списка
const removeCity = (index: number) => {
  tourStore.tour.cities.splice(index, 1)
}

// Функция для добавления города
const addCity = () => {
  if (selectedCityId.value) {
    citiesStore.cities.find((city) => city.name === selectedCityId.value)
  }
}
</script>

<style scoped>
.edit-tour {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 8px;
}

h2,
h3 {
  text-align: center;
}

.error-message {
  color: red;
  text-align: center;
  margin-bottom: 20px;
}

.tour-form {
  display: flex;
  flex-direction: column;
}

.form-group {
  margin-bottom: 10px;
}

label {
  font-weight: bold;
}

input,
textarea,
select {
  padding: 8px;
  margin-top: 5px;
  width: 100%;
  box-sizing: border-box;
}

button {
  margin-top: 10px;
  padding: 10px;
  background-color: #4caf50;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

button:hover {
  background-color: #45a049;
}

button:disabled {
  background-color: #aaa;
  cursor: not-allowed;
}

.loading-message {
  text-align: center;
  font-size: 16px;
  color: #333;
}

ul {
  list-style: none;
  padding: 0;
}

ul li {
  display: flex;
  justify-content: space-between;
  margin-bottom: 5px;
}
</style>

<template>
  <div class="tours-table">
    <h2>Список туров</h2>

    <!-- Сообщение об ошибке -->
    <div v-if="toursStore.isError" class="error-message">
      Произошла ошибка при загрузке туров. Пожалуйста, попробуйте снова.
    </div>

    <!-- Таблица туров -->
    <table v-if="toursStore.isLoading && !toursStore.isError">
      <thead>
        <tr>
          <th>Название</th>
          <th>Описание</th>
          <th>Цена</th>
          <th>Дата начала</th>
          <th>Дата окончания</th>
          <th>Компания</th>
          <th>Действия</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="tour in toursStore.tours" :key="tour.id">
          <td>{{ tour.title }}</td>
          <td>{{ tour.description }}</td>
          <td>{{ tour.price }} ₽</td>
          <td>{{ new Date(tour.startAt).toLocaleDateString() }}</td>
          <td>{{ new Date(tour.finishAt).toLocaleDateString() }}</td>
          <td>
            <button @click="viewDetails(tour.id)">Подробнее</button>
            <button @click="deleteTour(tour.id)">Удалить</button>
            <button @click="createContract(tour.id)">Составить контракт</button>
          </td>
        </tr>
      </tbody>
    </table>

    <!-- Индикатор загрузки -->
    <div v-if="toursStore.isLoading" class="loading-message">Загружаем туры...</div>
  </div>
</template>

<script lang="ts" setup>
import { onMounted } from 'vue'
import { useToursStore } from '@/stores/toursStore'
import { useRouter } from 'vue-router'

const toursStore = useToursStore()
const router = useRouter()

// Хранение данных о турах и их состоянии

// Загружаем туры при монтировании компонента
onMounted(() => {
  toursStore.fetchTours()
})

// Получаем имя компании по её ID

// Переводит пользователя на страницу с подробностями тура
const viewDetails = (tourId: string) => {
  router.push(`/tours/${tourId}`)
}

// Обрабатывает удаление тура
const deleteTour = (tourId: string) => {
  toursStore.deleteTour(tourId)
  toursStore.fetchTours()
}

// Обрабатывает создание контракта
const createContract = (tourId: string) => {
  router.push(`/tours/${tourId}/contract`)
}
</script>

<style scoped>
.tours-table {
  max-width: 1200px;
  margin: 20px auto;
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 8px;
}

h2 {
  text-align: center;
  margin-bottom: 20px;
}

table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 20px;
}

th,
td {
  padding: 12px;
  text-align: left;
  border-bottom: 1px solid #ddd;
}

th {
  background-color: #f4f4f4;
}

button {
  margin-right: 10px;
  padding: 8px 12px;
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

.loading-message,
.error-message {
  text-align: center;
  font-size: 16px;
  color: red;
  margin-top: 20px;
}
</style>

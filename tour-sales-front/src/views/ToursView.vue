<template>
  <div class="tours-table">
    <div class="search-bar"></div>
    <form>
      <input v-model="searchData.title" type="text" placeholder="Поиск по названию" />
      <select v-model="searchData.company">
        <option value="">Все компании</option>
        <option v-for="company in companiesStore.companies" :key="company.id" :value="company.id">
          {{ company.name }}
        </option>
      </select>
      <button type="submit" @click="find">Найти</button>
    </form>
    <h2>Список туров</h2>

    <!-- Сообщение об ошибке -->
    <div v-if="toursStore.isError" class="error-message">
      Произошла ошибка при загрузке туров. Пожалуйста, попробуйте снова.
    </div>

    <!-- Таблица туров -->
    <table v-if="!toursStore.isLoading && !toursStore.isError">
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
          <td v-if="tour.company">{{ tour.company.name }}</td>
          <td class="actions">
            <button @click.prevent="viewDetails(tour.id)">Подробнее</button>
            <button @click.prevent="deleteTour(tour.id)">Удалить</button>
            <button @click.prevent="createContract(tour.id)">Составить контракт</button>
          </td>
        </tr>
      </tbody>
    </table>

    <!-- Индикатор загрузки -->
    <div v-if="toursStore.isLoading" class="loading-message">Загружаем туры...</div>
  </div>
</template>

<script lang="ts" setup>
import { onMounted, ref } from 'vue'
import { useToursStore } from '@/stores/toursStore'
import { useRouter } from 'vue-router'
import { useCompaniesStore } from '@/stores/companiesStore'

const companiesStore = useCompaniesStore()
const toursStore = useToursStore()
const router = useRouter()
const searchData = ref({
  title: '',
  company: '',
})

const find = (e: Event) => {
  e.preventDefault()
  toursStore.fetchToursByTitleAndCompanyId(searchData.value.title, searchData.value.company)
}

// Хранение данных о турах и их состоянии

// Загружаем туры при монтировании компонента
onMounted(() => {
  toursStore.fetchToursByTitleAndCompanyId(searchData.value.title, searchData.value.company)
  console.log(toursStore.tours)
  console.log('mounted')
  companiesStore.fetchCompanies()
})

const viewDetails = (tourId: string) => {
  router.push(`/tour/${tourId}`)
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
  min-width: 1200px;
  margin: 20px auto;
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 8px;
}

select {
  background-color: var(--color-dark-green);
}

input {
  background-color: var(--color-dark-green);
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
  background-color: var(--color-green);

  border-bottom: 1px solid #ddd;
}

th {
  background-color: var(--color-dark-green);
}

button {
  margin-right: 10px;
  padding: 8px 12px;
  background-color: var(--color-dark-green);
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.actions {
  display: flex;
  gap: 10px;
  flex-direction: column;
}

button:hover {
  background-color: var(--color-light-brown);
}

button:disabled {
  background-color: var(--color-green);
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

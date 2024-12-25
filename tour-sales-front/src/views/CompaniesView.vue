<template>
  <div class="companies-list">
    <h2>Список компаний</h2>

    <!-- Сообщение о загрузке -->
    <div v-if="companiesStore.isLoading" class="loading-message">Загрузка...</div>

    <!-- Сообщение об ошибке -->
    <div v-if="companiesStore.isError" class="error-message">
      Произошла ошибка при загрузке компаний.
    </div>

    <!-- Таблица с компаниями -->
    <table v-if="!companiesStore.isLoading && !companiesStore.isError" class="companies-table">
      <thead>
        <tr>
          <th>Название</th>
          <th>Электронная почта</th>
          <th>Описание</th>
          <th>Действия</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="company in companiesStore.companies" :key="company.id">
          <td>{{ company.name }}</td>
          <td>{{ company.email }}</td>
          <td>{{ company.description }}</td>
          <td style="display: flex; flex-direction: column; gap: 5px">
            <button>Туры</button>
            <button v-if="authStore.isAdmin()" @click="toUpdateCompany(company.id)">
              Редактировать
            </button>
            <button v-if="authStore.isAdmin()">Удалить</button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script lang="ts" setup>
import { onMounted } from 'vue'
import { useCompaniesStore } from '@/stores/companiesStore'
import { useAuthStore } from '@/stores/authStore'
import { useRouter } from 'vue-router'

const companiesStore = useCompaniesStore()
const authStore = useAuthStore()

const router = useRouter()

const toUpdateCompany = (id: string) => {
  router.push(`/update-company/${id}`)
}

onMounted(() => {
  companiesStore.fetchCompanies()
  console.log(companiesStore.companies)
})
</script>

<style scoped>
.companies-list {
  padding: 20px;
  max-width: 800px;
  margin: 0 auto;
}

h2 {
  text-align: center;
  margin-bottom: 20px;
}

.loading-message,
.error-message {
  text-align: center;
  font-size: 16px;
  margin-top: 20px;
}

.companies-table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 20px;
}

.companies-table th,
.companies-table td {
  padding: 12px;
  text-align: left;
  border-bottom: 1px solid #ccc;
}

.companies-table th {
  background-color: var(--color-dark-green);
}

button {
  background-color: #ff4747;
  color: white;
  border: none;
  padding: 6px 12px;
  cursor: pointer;
  border-radius: 4px;
}

button:hover {
  background-color: #e63946;
}
</style>

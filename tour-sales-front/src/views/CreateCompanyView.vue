<template>
  <div class="create-company">
    <!-- Заголовок -->
    <h2>Создать компанию</h2>

    <!-- Форма для создания компании -->
    <form @submit.prevent="submitForm" class="company-form">
      <div class="form-group">
        <label for="name">Название компании</label>
        <input
          type="text"
          id="name"
          v-model="companyData.name"
          placeholder="Введите название компании"
          required
        />
      </div>

      <div class="form-group">
        <label for="email">Электронная почта</label>
        <input
          type="text"
          id="email"
          v-model="companyData.email"
          placeholder="Введите электронную почту"
          required
        />
      </div>

      <div class="form-group">
        <label for="description">Описание компании</label>
        <textarea
          id="description"
          v-model="companyData.description"
          placeholder="Введите описание компании"
          rows="4"
          required
        ></textarea>
      </div>

      <button type="submit" :disabled="isLoading">Создать</button>
    </form>

    <!-- Сообщение об ошибке -->
    <div v-if="isError" class="error-message">Произошла ошибка при создании компании.</div>

    <!-- Сообщение о загрузке -->
    <div v-if="isLoading" class="loading-message">Создание компании...</div>
  </div>
</template>

<script lang="ts" setup>
import { ref } from 'vue'
import { useCompaniesStore } from '@/stores/companiesStore'

const companiesStore = useCompaniesStore()

// Локальные данные для формы
const companyData = ref({
  name: '',
  email: '',
  description: '',
})

// Локальные состояния для ошибки и загрузки
const isLoading = ref(false)
const isError = ref(false)

// Функция для отправки формы
const submitForm = async () => {
  // Проверяем, что все поля заполнены
  if (
    companyData.value.name.trim() &&
    companyData.value.email.trim() &&
    companyData.value.description.trim()
  ) {
    isLoading.value = true
    isError.value = false
    try {
      // Создаем компанию через Pinia store
      await companiesStore.createCompany(companyData.value)
      // После успешного создания очищаем форму
      companyData.value.name = ''
      companyData.value.email = ''
      companyData.value.description = ''
    } catch (error) {
      console.error('Произошла ошибка при создании компании:', error)
      isError.value = true
    } finally {
      isLoading.value = false
    }
  }
}
</script>

<style scoped>
.create-company {
  width: 500px;
  margin: 0 auto;
}

h2 {
  text-align: center;
  margin-bottom: 20px;
}

.company-form {
  display: flex;
  flex-direction: column;
  gap: 15px;
}

.form-group {
  display: flex;
  flex-direction: column;
}

.form-group label {
  font-size: 14px;
  margin-bottom: 5px;
}

.form-group input,
.form-group textarea {
  padding: 10px;
  border: 1px solid #ccc;
  background-color: var(--color-dark-green);
  border-radius: 5px;
  font-size: 14px;
}
input::placeholder {
  color: var(--color-light-brown);
}

form button {
  padding: 10px;
  background-color: var(--color-light-brown);
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 16px;
}

form button:disabled {
  background-color: var(--color-green);
}

.error-message {
  color: var(--color-light-brown);
  text-align: center;
  margin-top: 20px;
}

.loading-message {
  text-align: center;
  margin-top: 20px;
}
</style>

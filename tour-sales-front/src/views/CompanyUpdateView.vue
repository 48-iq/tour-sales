<template>
  <div class="edit-company">
    <h2>Редактировать компанию</h2>

    <!-- Сообщение о загрузке -->
    <div v-if="companyStore.isLoading" class="loading-message">Загрузка...</div>

    <!-- Сообщение об ошибке -->
    <div v-if="companyStore.isError" class="error-message">
      Произошла ошибка при загрузке или сохранении данных.
    </div>

    <!-- Форма редактирования компании -->
    <form v-if="!companyStore.isLoading && !companyStore.isError" @submit.prevent="onSubmit">
      <div class="form-group">
        <label for="name">Название компании:</label>
        <input
          type="text"
          id="name"
          v-model="companyStore.company.name"
          placeholder="Введите название компании"
          required
        />
      </div>

      <div class="form-group">
        <label for="email">Электронная почта:</label>
        <input
          type="email"
          id="email"
          v-model="companyStore.company.email"
          placeholder="Введите почту компании"
          required
        />
      </div>

      <div class="form-group">
        <label for="description">Описание:</label>
        <textarea
          id="description"
          v-model="companyStore.company.description"
          placeholder="Введите описание компании"
          required
        ></textarea>
      </div>

      <button type="submit" :disabled="companyStore.isLoading">Сохранить изменения</button>
    </form>
  </div>
</template>

<script lang="ts" setup>
import { defineProps, onMounted } from 'vue'
import { useCompanyStore } from '@/stores/companyStore'

// Получаем ID компании из пропсов
const props = defineProps<{
  id: string
}>()

// Получаем доступ к хранилищу компании
const companyStore = useCompanyStore()

// Загружаем информацию о компании при монтировании компонента
onMounted(() => {
  companyStore.fetchCompany(props.id)
})

// Обработчик отправки формы
const onSubmit = async () => {
  await companyStore.updateCompany(companyStore.company)
}
</script>

<style scoped>
.edit-company {
  padding: 20px;
  max-width: 600px;
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

.form-group {
  margin-bottom: 15px;
}

.form-group label {
  display: block;
  font-weight: bold;
}

.form-group input,
.form-group textarea {
  width: 100%;
  padding: 10px;
  margin-top: 5px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

button {
  background-color: #4caf50;
  color: white;
  border: none;
  padding: 10px 20px;
  cursor: pointer;
  border-radius: 4px;
  width: 100%;
}

button:disabled {
  background-color: #aaa;
}

button:hover:not(:disabled) {
  background-color: #45a049;
}
</style>

<template>
  <div class="create-tour">
    <h2>Создать новый тур</h2>

    <!-- Сообщение об ошибке -->
    <div v-if="toursStore.isError" class="error-message">
      Произошла ошибка. Пожалуйста, попробуйте снова.
    </div>

    <!-- Форма для создания тура -->
    <form @submit.prevent="onSubmit" v-if="!toursStore.isLoading && !toursStore.isError">
      <div class="form-group">
        <label for="title">Название тура</label>
        <input
          id="title"
          v-model="tour.title"
          type="text"
          placeholder="Введите название тура"
          required
        />
      </div>

      <div class="form-group">
        <label for="description">Описание</label>
        <textarea
          id="description"
          v-model="tour.description"
          placeholder="Введите описание тура"
          required
        ></textarea>
      </div>

      <div class="form-group">
        <label for="price">Цена</label>
        <input
          id="price"
          v-model.number="tour.price"
          type="number"
          placeholder="Введите цену"
          required
        />
      </div>

      <div class="form-group">
        <label for="startAt">Дата начала</label>
        <input id="startAt" v-model="tour.startAt" type="date" required />
      </div>

      <div class="form-group">
        <label for="finishAt">Дата окончания</label>
        <input id="finishAt" v-model="tour.finishAt" type="date" required />
      </div>

      <div class="form-group">
        <label for="availableCount">Доступные места</label>
        <input
          id="availableCount"
          v-model.number="tour.availableCount"
          type="number"
          placeholder="Введите количество доступных мест"
          required
        />
      </div>

      <div class="form-group">
        <label for="companyId">Компания</label>
        <select id="companyId" v-model="tour.companyId" required>
          <option value="" disabled selected>Выберите компанию</option>
          <option v-for="company in companiesStore.companies" :key="company.id" :value="company.id">
            {{ company.name }}
          </option>
        </select>
      </div>

      <div class="form-group">
        <button type="submit" :disabled="toursStore.isLoading">Создать тур</button>
      </div>
    </form>

    <!-- Индикатор загрузки -->
    <div v-if="toursStore.isLoading" class="loading-message">Создание тура...</div>
  </div>
</template>

<script lang="ts" setup>
import { ref, onMounted } from 'vue'
import { useToursStore } from '@/stores/toursStore'
import { useCompaniesStore } from '@/stores/companiesStore'

const toursStore = useToursStore()
const companiesStore = useCompaniesStore()

// Модели для формы
const tour = ref({
  title: '',
  description: '',
  price: 0,
  startAt: '',
  finishAt: '',
  availableCount: 0,
  companyId: '',
})

// Список компаний

// Загружаем список компаний при монтировании компонента
onMounted(() => {
  companiesStore.fetchCompanies()
})

// Обработчик отправки формы
const onSubmit = async () => {
  const newTour = {
    ...tour.value,
  }

  await toursStore.createTour(newTour)

  if (!toursStore.isError) {
    // После успешного создания тура, можно очистить форму или показать сообщение о успехе
    tour.value = {
      title: '',
      description: '',
      price: 0,
      startAt: '',
      finishAt: '',
      availableCount: 0,
      companyId: '',
    }
  }
}
</script>

<style scoped>
.create-tour {
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
}

h2 {
  text-align: center;
  margin-bottom: 20px;
}

.form-group {
  margin-bottom: 15px;
}

.form-group label {
  font-weight: bold;
  display: block;
}

.form-group input,
.form-group textarea,
.form-group select {
  width: 100%;
  padding: 10px;
  margin-top: 5px;
  border: 1px solid var(--color-green);
  background-color: var(--color-dark-green);
  border-radius: 5px;
}

button {
  background-color: var(--color-green);
  padding: 10px 20px;
  border: none;
  cursor: pointer;
  border-radius: 5px;
  width: 100%;
}

button:disabled {
  background-color: var(--color-dark-green);
}

button:hover:not(:disabled) {
  background-color: var(--color-green);
}

.loading-message,
.error-message {
  text-align: center;
  font-size: 16px;
  color: var(--color-light-green);
  margin-top: 20px;
}
</style>

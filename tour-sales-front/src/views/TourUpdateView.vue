<template>
  <div class="edit-tour">
    <h2>Редактирование тура</h2>

    <!-- Сообщение об ошибке -->
    <div v-if="tourStore.isError" class="error-message">
      Произошла ошибка при загрузке или обновлении тура.
    </div>

    <!-- Форма редактирования -->
    <form class="tour-form" @submit.prevent="onSubmit">
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

      <div class="form-group">
        <label for="availableCount">Доступные места</label>
        <input v-model="tourStore.tour.availableCount" id="availableCount" type="number" required />
      </div>

      <button type="submit" :disabled="tourStore.isLoading">Сохранить</button>
    </form>

    <br />
    <br />

    <h3>Города тура</h3>

    <!-- Выпадающий список для добавления города -->
    <div>
      <label for="add-city">Добавить город</label>
      <select v-model="addCityName" id="add-city">
        <option v-for="city in citiesStore.cities" :key="city.name" :value="city.name">
          {{ city.name }}
        </option>
      </select>
      <button @click.prevent="addCity">Добавить</button>
    </div>

    <!-- Список городов -->
    <div>
      <ul>
        <li v-for="city in tourStore.tour.cities" :key="city.name">
          {{ city.name }}
          <button>Удалить</button>
        </li>
      </ul>
    </div>

    <!-- Форма для создания скидки -->
    <h3>Добавить скидку</h3>
    <div class="discount-form">
      <div class="form-group">
        <label for="discount-category">Категория</label>
        <select v-model="selectedDiscountCategory" id="discount-category" required>
          <option
            v-for="category in userCategoriesStore.userCategories"
            :key="category.title"
            :value="category.title"
          >
            {{ category.title }}
          </option>
        </select>
      </div>

      <div class="form-group">
        <label for="discount-value">Скидка (%)</label>
        <input
          v-model="discountValue"
          id="discount-value"
          type="number"
          min="0"
          max="100"
          required
        />
      </div>

      <button>Добавить скидку</button>
    </div>

    <!-- Список скидок -->
    <h3>Скидки</h3>
    <div>
      <ul>
        <li v-for="(discount, index) in tourStore.tour.discounts" :key="index">
          {{ discount.categoryName }} - {{ discount.discount }}%
          <button>Удалить</button>
        </li>
      </ul>
    </div>

    <div v-if="tourStore.isLoading" class="loading-message">Сохранение...</div>
  </div>
</template>

<script lang="ts" setup>
import { ref, onMounted } from 'vue'
import { useTourStore } from '@/stores/tourStore'
import { useCitiesStore } from '@/stores/citiesStore'
import { useUserCategoriesStore } from '@/stores/userCategoriesStore'

const params = defineProps<{ id: string }>()

// Состояния компонента
const tourStore = useTourStore()
const citiesStore = useCitiesStore()
const userCategoriesStore = useUserCategoriesStore()

onMounted(() => {
  tourStore.fetchTour(params.id)
  citiesStore.fetchCities()
  userCategoriesStore.fetchUserCategories()
})

const onSubmit = async () => {
  await tourStore.updateTour(tourStore.tour)
}

const addCityName = ref<string>('')

const addCity = async () => {
  tourStore.addCity(params.id, addCityName.value)
  addCityName.value = ''
}

const selectedDiscountCategory = ref<string | null>(null)
const discountValue = ref<number | null>(null)
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
  color: var(--color-light-brown);
  text-align: center;
  margin-bottom: 20px;
}

.tour-form,
.discount-form {
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
  background-color: var(--color-dark-green);
  padding: 8px;
  margin-top: 5px;
  width: 100%;
  box-sizing: border-box;
}

button {
  margin-top: 10px;
  padding: 10px;
  background-color: var(--color-green);
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

button:hover {
  background-color: var(--color-green);
}

button:disabled {
  background-color: var(--color-green);
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

ul li button {
  background-color: var(--color-light-brown);
  color: white;
}
</style>

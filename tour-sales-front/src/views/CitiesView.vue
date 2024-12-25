<template>
  <div class="cities">
    <!-- Форма для добавления города -->
    <div class="add-city-form">
      <input v-model="newCity.name" placeholder="Введите название города" />
      <select v-model="newCity.countryName" placeholder="Выберите страну">
        <option value="" disabled>Выберите страну</option>
        <option
          v-for="country in countriesStore.countries"
          :key="country.name"
          :value="country.name"
        >
          {{ country.name }}
        </option>
      </select>
      <button @click="addCity" :disabled="citiesStore.isLoading">Создать город</button>
    </div>

    <!-- Сообщение об ошибке -->
    <div v-if="citiesStore.isError" class="error-message">
      Произошла ошибка при загрузке данных или добавлении города.
    </div>

    <!-- Список городов -->
    <div v-if="!citiesStore.isLoading && citiesStore.cities.length > 0" class="city-list">
      <div v-for="city in citiesStore.cities" :key="city.name" class="city-item">
        <div class="city-info">
          <h3>{{ city.name }}</h3>
          <p>{{ city.countryName }}</p>
        </div>
        <button @click="deleteCity(city.name)" :disabled="citiesStore.isLoading">Удалить</button>
      </div>
    </div>

    <!-- Загрузка данных -->
    <div v-if="citiesStore.isLoading" class="loading-message">Загрузка...</div>
  </div>
</template>

<script lang="ts" setup>
import { ref, onMounted } from 'vue'
import { useCitiesStore } from '@/stores/citiesStore'
import { useCountriesStore } from '@/stores/countriesStore'

// Подключаем Pinia store для работы с городами и странами
const citiesStore = useCitiesStore()
const countriesStore = useCountriesStore()

// Локальные данные для нового города
const newCity = ref({
  name: '',
  countryName: '',
})

// Получаем список стран из хранилища (предполагается, что они уже загружены в хранилище)

// Функция для добавления города
const addCity = () => {
  if (newCity.value.name.trim() && newCity.value.countryName) {
    console.log(newCity.value)
    citiesStore.createCity(newCity.value)
    newCity.value.name = '' // Очищаем поле после добавления
    newCity.value.countryName = '' // Сбрасываем выбор страны
  }
}

// Функция для удаления города
const deleteCity = (name: string) => {
  citiesStore.deleteCity(name)
}

// Загружаем список городов при монтировании компонента
onMounted(() => {
  countriesStore.fetchCountries()
  citiesStore.fetchCities()
})
// Подключаем данные из Pinia store
</script>

<style scoped>
.cities {
  width: 400px;
  margin: 0 auto;
}

.add-city-form {
  display: flex;
  flex-direction: column;
  gap: 10px;
}

.add-city-form input,
.add-city-form select {
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  background-color: var(--color-dark-green);
}

.add-city-form button {
  padding: 10px;
  background-color: var(--color-green);
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

.add-city-form button:disabled {
  background-color: #cccccc;
}

.error-message {
  color: var(--color-light-brown);
  margin-top: 20px;
}

.city-list {
  margin-top: 20px;
}

.city-item {
  display: flex;
  justify-content: space-between;
  padding: 10px;
  border: 1px solid #ddd;
  margin-bottom: 10px;
  border-radius: 5px;
}

.city-item button {
  background-color: var(--color-light-brown);
  color: white;
  border: none;
  padding: 5px 10px;
  cursor: pointer;
}

.city-item button:disabled {
  background-color: #cccccc;
}

.loading-message {
  text-align: center;
  margin-top: 20px;
}
</style>

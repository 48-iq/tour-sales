<template>
  <div class="tour-details">
    <h2>Информация о туре</h2>

    <!-- Сообщение об ошибке -->
    <div v-if="tourStore.isError" class="error-message">Произошла ошибка при загрузке тура.</div>

    <!-- Основная информация о туре -->
    <div class="tour-info">
      <div class="form-group">
        <label>Название тура</label>
        <p>{{ tourStore.tour.title }}</p>
      </div>

      <div class="form-group">
        <label>Описание</label>
        <p>{{ tourStore.tour.description }}</p>
      </div>

      <div class="form-group">
        <label>Цена</label>
        <p>{{ tourStore.tour.price }}</p>
      </div>

      <div class="form-group">
        <label>Количество мест</label>
        <p>{{ tourStore.tour.availableCount }}</p>
      </div>

      <div class="form-group">
        <label>Дата начала</label>
        <p>{{ tourStore.tour.startAt }}</p>
      </div>

      <div class="form-group">
        <label>Дата окончания</label>
        <p>{{ tourStore.tour.finishAt }}</p>
      </div>
    </div>

    <BaseButton @click.prevent="() => router.push(`/tour-update/${props.id}`)"
      >Редактировать</BaseButton
    >

    <!-- Города тура -->
    <h3>Города тура</h3>
    <div>
      <ul>
        <li v-for="city in tourStore.tour.cities" :key="city.name">
          {{ city.name }}
        </li>
      </ul>
    </div>
    <div>
      <p>Города не добавлены</p>
    </div>

    <!-- Скидки -->
    <h3>Скидки</h3>
    <div>
      <ul>
        <li v-for="(discount, index) in tourStore.tour.discounts" :key="index">
          {{ discount.categoryName }} - {{ discount.discount }}%
        </li>
      </ul>
    </div>
    <div>
      <p>Скидки не добавлены</p>
    </div>

    <div v-if="tourStore.isLoading" class="loading-message">Загрузка...</div>
  </div>
</template>

<script lang="ts" setup>
import { onMounted } from 'vue'
import { useTourStore } from '@/stores/tourStore'
import BaseButton from '@/components/buttons/BaseButton.vue'
import router from '@/router'

// Состояние компонента
const tourStore = useTourStore()

const props = defineProps<{ id: string }>()

// Загружаем тур по переданному id
onMounted(() => {
  tourStore.fetchTour(props.id)
})
</script>

<style scoped>
.tour-details {
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

.tour-info {
  display: flex;
  flex-direction: column;
  margin-bottom: 20px;
}

.form-group {
  margin-bottom: 10px;
}

label {
  font-weight: bold;
}

p {
  margin: 5px 0;
  font-size: 1rem;
}

ul {
  list-style: none;
  padding: 0;
}

ul li {
  font-size: 1rem;
  margin-bottom: 5px;
}

.loading-message {
  text-align: center;
  font-size: 16px;
  color: #333;
}
</style>

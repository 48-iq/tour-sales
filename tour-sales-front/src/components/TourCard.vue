<script setup lang="ts">
  import { defineProps } from 'vue';
  type Tour = {
    id: string,
    title: string,
    description: string,
    createdAt: string,
    price: number,
    startAt: string,
    finishAt: string,
    availableCount: number,
    companyId: string
  }
  type TourCardProps = {
    tour: Tour
  }
  const props = defineProps<TourCardProps>()
  const tour = props.tour

</script>
<template>
  <div class="tour-card">
    <h1>Карточка тура</h1>
    <p>id: {{ tour.id }}</p>
    <p>Название {{ tour.title }}</p>
    <p>Описание: {{ tour.description }}</p>
    <p>Время начала: {{ tour.startAt }}</p>
    <p>Время окончания: {{ tour.finishAt }}</p>
    <p>Количество мест: {{ tour.availableCount }}</p>
    <p>Дата создания: {{ tour.createdAt }}</p>
    <p>Компания: {{ tour.companyId }}</p>
    <p>Цена: {{ tour.price }}</p>
  </div>
</template>

<style scoped>
  .tour-card {
    border: 2px solid var(--color-light-green);
    width: 505px;
    padding: 10px;
    background-color: var(--color-white);
  }
</style>

<script setup lang="ts">
import StandardButton from '@/components/StandardButton.vue';
import StandardInput from '@/components/StandardInput.vue';
import TourCard from '@/components/TourCard.vue';

const tour = {
  id: '111111-1111111-1111111',
  title: 'Тур в Москву',
  description: 'Описание тура',
  startAt: '2023-01-01',
  finishAt: '2023-01-01',
  availableCount: 10,
  createdAt: '2023-01-01 00:00:00',
  price: 1000,
  companyId: '111111-1111111-1111111',
}


</script>
<template>
  <div>
    <div class="view">
      <h1>Туры</h1>
      <div>
        <TourCard :tour="tour"/>

      </div>
    </div>
  </div>
</template>

<style scoped>
  .view {
    display: flex;
    flex-direction: column;
    gap: 10px;
    align-items: center;
  }
  .form {
    display: flex;
    select {
      margin-left: 10px;
      border: 2px solid var(--color-light-green);
      width: 100px;
    }
    select:focus {
      border: 2px solid var(--color-light-green);
    }
  }
  .find {
    background-color: var(--color-white);
    margin-left: 10px;
    width: 200px;
  }
  .find-input {
    width: 300px;
  }
</style>

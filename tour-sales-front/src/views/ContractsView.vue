<script setup lang="ts">
import ContractCard from '@/components/ContractCard.vue';

const contract = {
  id: '11111111',
  userId: '11111111',
  tourId: '11111111',
  price: 1000,
  wasPayed: false
}

</script>
<template>
  <div>
    <div class="view">
      <h1>Контракты</h1>
      <div>
        <ContractCard :contract="contract"/>
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

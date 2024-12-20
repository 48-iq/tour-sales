<script setup lang="ts">
  import { defineProps } from 'vue';
  type Contract = {
    id: string,
    tourId: string,
    userId: string,
    price: number,
    wasPayed: boolean
  }
  type ContractCardProps = {
    contract: Contract
  }
  const props = defineProps<ContractCardProps>()
  const contract = props.contract

</script>
<template>
  <div class="contract-card">
    <h1>Карточка контракта</h1>
    <p>id: {{ contract.id }}</p>
    <p>Тур {{ contract.tourId }}</p>
    <p>Пользователь: {{ contract.userId }}</p>
    <p>Цена: {{ contract.price }}</p>
    <p>Оплачено: {{ contract.wasPayed }}</p>
  </div>
</template>

<style scoped>
  .contract-card {
    border: 2px solid var(--color-light-green);
    width: 505px;
    padding: 10px;
    background-color: var(--color-white);
  }
</style>

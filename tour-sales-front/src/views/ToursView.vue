<script setup lang="ts">
import StandardInput from '@/components/StandardInput.vue';

</script>
<template>
  <div>
    <div class="view">
      <h1>Туры</h1>
      <form>
        <StandardInput placeholder="Название"/>
        <select>
          <option>Все</option>
        </select>
      </form>
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
</style>

<template>
  <div class="view">
    <!-- Форма для создания контракта -->
    <div>
      <h2>Создать контракт</h2>
      <form
        @submit.prevent="
          () => contractsStore.createContract({ tourId: selectedTourId, userId: selectedUserId })
        "
        class="create-contract-form"
      >
        <label for="user">Выберите пользователя</label>
        <div>
          <select v-model="selectedUserId" id="user" required>
            <option v-for="user in contractsStore.users" :key="user.id" :value="user.id">
              {{ user.name }}
            </option>
          </select>
        </div>
        <label for="tour">Выберите тур</label>
        <div>
          <select v-model="selectedTourId" id="tour" required>
            <option v-for="tour in toursStore.tours" :key="tour.id" :value="tour.id">
              {{ tour.title }}
            </option>
          </select>
        </div>
        <button type="submit" :disabled="contractsStore.isLoading">Создать</button>
      </form>
      <div v-if="contractsStore.isError" class="error-message">
        Произошла ошибка при создании контракта.
      </div>
    </div>

    <!-- Список контрактов -->
    <div class="contracts-list">
      <h2>Список контрактов</h2>
      <ul>
        <li v-for="contract in contractsStore.contracts" :key="contract.id">
          <div>
            <strong>Тур:</strong
            ><RouterLink :to="`/tour/${contract.tourId}`"> {{ contract.tourId }} </RouterLink> |
            <strong>Пользователь:</strong>
            <RouterLink :to="`/userS/${contract.userId}`"> {{ contract.userId }}</RouterLink> |
            <strong>Цена:</strong> {{ contract.price }}
          </div>
          <button
            @click="contractsStore.deleteContract(contract.id)"
            :disabled="contractsStore.isLoading"
          >
            Удалить
          </button>
        </li>
      </ul>
    </div>

    <!-- Загрузка -->
    <div v-if="contractsStore.isLoading">Загрузка...</div>
  </div>
</template>

<script lang="ts" setup>
import { ref, onMounted, watch } from 'vue'
import { useContractsStore } from '@/stores/contractsStore'
import { useToursStore } from '@/stores/toursStore'
import { RouterLink } from 'vue-router'

const contractsStore = useContractsStore()
const toursStore = useToursStore()

const selectedUserId = ref<string>('')
const selectedTourId = ref<string>('')

// Загружаем контракты при монтировании компонента
onMounted(() => {
  contractsStore.fetchContracts()
  contractsStore.fetchUsers()
  toursStore.fetchToursByTitleAndCompanyId('', '')
})

watch(
  () => contractsStore.contracts,
  () => {
    console.log(contractsStore.contracts)
  },
)
</script>

<style scoped>
.view {
  padding: 30px;
}

.create-contract-form {
  display: grid;
  gap: 10px;
  grid-template-columns: 1fr 1fr;
  width: 600px;
  padding: 30px;
  margin-bottom: 20px;
}

.create-contract-form form {
  display: flex;
  flex-direction: column;
}

.create-contract-form label {
  margin-bottom: 8px;
}

.create-contract-form select {
  margin-bottom: 12px;
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
  background-color: var(--color-dark-green);
  width: 300px;
}

.create-contract-form button {
  padding: 8px 16px;
  border: none;
  background-color: #4caf50;
  color: white;
  cursor: pointer;
  border-radius: 4px;
}

.create-contract-form button:disabled {
  background-color: #ccc;
}

.contracts-list {
  margin-top: 30px;
}

.contracts-list ul {
  list-style-type: none;
  padding: 0;
}

.contracts-list li {
  display: flex;
  justify-content: space-between;
  padding: 10px 0;
  border-bottom: 1px solid #eee;
}

.contracts-list button {
  padding: 6px 12px;
  background-color: var(--color-light-brown);
  color: white;
  cursor: pointer;
  border: none;
  border-radius: 4px;
}

.contracts-list button:disabled {
  background-color: var(--color-dark-green);
}

.error-message {
  color: red;
  margin-top: 10px;
}
</style>

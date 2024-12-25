<script lang="ts" setup>
import AdminNav from '@/components/general/AdminNav.vue'
import UserNav from '@/components/general/UserNav.vue'
import { useAuthStore } from '@/stores/authStore'

const authStore = useAuthStore()
</script>

<template>
  <div class="page">
    <div class="nav-container">
      <UserNav />
      <AdminNav v-if="authStore.isAdmin()" />
    </div>
    <RouterView />
  </div>
</template>
<style scoped>
.page {
  width: 100vw;
  height: 100vh;
  padding: 10px;
  display: grid;
  grid-template-columns: 175px auto;
  column-gap: 10px;
}
.nav-container {
  display: flex;
  flex-direction: column;
  gap: 10px;
}
</style>

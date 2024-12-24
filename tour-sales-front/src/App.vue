<script setup lang="ts">
import { RouterView } from 'vue-router'
import { useAuthStore } from './stores/authStore'
import { onMounted } from 'vue'
const authStore = useAuthStore()
import 'ant-design-vue/dist/reset.css'

onMounted(() => {
  authStore.loadCookies()
})
</script>

<template>
  <RouterView />
</template>

<style scoped></style>

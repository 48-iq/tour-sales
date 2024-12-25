<script setup lang="ts">
import BaseButton from '@/components/buttons/BaseButton.vue'
import UserProfile from '@/components/user/UserProfile.vue'
import UserCategoryList from '@/components/user/UserCategoryList.vue'
import { useAuthStore } from '@/stores/authStore'
import { useUserStore } from '@/stores/userStore'
import { watch } from 'vue'
import { useRouter } from 'vue-router'

const props = defineProps<{ id: string }>()

const userStore = useUserStore()

const authStore = useAuthStore()

const router = useRouter()

userStore.fetchUser(props.id)

watch(
  () => props.id,
  () => {
    userStore.fetchUser(props.id)
  },
)
</script>

<template>
  <div class="view">
    <UserProfile :user="userStore.user" />
    <div class="buttons">
      <BaseButton v-if="userStore.isCurrentUser()" @click="router.push(`/users/${props.id}/edit`)">
        Редактировать</BaseButton
      >
      <BaseButton v-if="userStore.isCurrentUser()" @click="authStore.logout()">Выйти</BaseButton>
    </div>
    <UserCategoryList :categories="userStore.user.categories" />
  </div>
</template>

<style scoped>
.view {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 10px;
  width: 800px;
}

.buttons {
  display: flex;
  gap: 10px;
  align-items: center;
  width: 600px;
}
</style>

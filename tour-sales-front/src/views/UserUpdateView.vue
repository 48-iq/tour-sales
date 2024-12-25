<script setup lang="ts">
import UserUpdateForm from '@/components/forms/UserUpdateForm.vue'
import AddUserCategory from '@/components/user/AddUserCategory.vue'
import UserCategoryEditList from '@/components/user/UserCategoryEditList.vue'
import { useUserUpdateStore } from '@/stores/userUpdateStore'
import { watch } from 'vue'

const props = defineProps<{ id: string }>()

const userUpdateStore = useUserUpdateStore()

userUpdateStore.fetchUser(props.id)

watch(
  () => props.id,
  () => {
    userUpdateStore.fetchUser(props.id)
  },
)
</script>

<template>
  <div class="view">
    <UserUpdateForm :user="userUpdateStore.user" />
    <AddUserCategory />
    <UserCategoryEditList :categories="userUpdateStore.user.categories" />
  </div>
</template>

<style scoped>
.view {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 800px;
  gap: 10px;
}
</style>

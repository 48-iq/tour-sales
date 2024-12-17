import LoginPage from '@/views/LoginPage.vue'
import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/login',
      name: 'login',
      component: LoginPage,
    },
    {
      path: '/:catchAll(.*)',
      name: 'any',
      component: LoginPage
    }

  ],
})

export default router

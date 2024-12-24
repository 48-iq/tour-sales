import AuthPage from '@/views/AuthPage.vue'
import LoginView from '@/views/LoginView.vue'
import NotFoundPage from '@/views/NotFoundPage.vue'
import RegisterView from '@/views/RegisterView.vue'
import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      name: 'auth',
      path: '/auth',
      component: AuthPage,
      children: [
        {
          name: 'login',
          path: 'login',
          component: LoginView,
        },
        {
          name: 'register',
          path: 'register',
          component: RegisterView,
        },
      ],
    },
    {
      name: 'any',
      path: '/:pathMatch(.*)*',
      component: NotFoundPage,
    },
    {
      name: 'root',
      path: '/',
      redirect: '/auth/login',
    },
  ],
})

export default router

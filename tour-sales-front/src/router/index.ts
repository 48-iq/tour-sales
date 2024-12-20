import ContractsView from '@/views/ContractsView.vue'
import HomePage from '@/views/HomePage.vue'
import LoginPage from '@/views/LoginPage.vue'
import ProfileEditView from '@/views/ProfileEditView.vue'
import ProfileView from '@/views/ProfileView.vue'
import RegisterPage from '@/views/RegisterPage.vue'
import ToursView from '@/views/ToursView.vue'
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
      path: '/register',
      name: 'register',
      component: RegisterPage,
    },
    {
      path: '/home',
      name: 'home',
      component: HomePage,
      children: [
        {
          path: 'profile',
          name: 'profile',
          component: ProfileView
        },
        {
          path: 'profile-edit',
          name: 'profile-edit',
          component: ProfileEditView
        },
        {
          path: 'tours',
          name: 'tours',
          component: ToursView
        },
        {
          path: 'contracts',
          name: 'contracts',
          component: ContractsView
        }
      ]
    },
    {
      path: '/:catchAll(.*)',
      name: 'any',
      component: LoginPage
    },

  ],
})

export default router

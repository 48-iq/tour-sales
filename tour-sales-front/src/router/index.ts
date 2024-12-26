import AuthPage from '@/views/AuthPage.vue'
import HomePage from '@/views/HomePage.vue'
import LoginView from '@/views/LoginView.vue'
import NotFoundPage from '@/views/NotFoundPage.vue'
import UserView from '@/views/UserView.vue'
import RegisterView from '@/views/RegisterView.vue'
import { createRouter, createWebHistory } from 'vue-router'
import UserUpdateView from '@/views/UserUpdateView.vue'
import CategoriesView from '@/views/CategoriesView.vue'
import CountriesView from '@/views/CountriesView.vue'
import CitiesView from '@/views/CitiesView.vue'
import CompaniesView from '@/views/CompaniesView.vue'
import CreateCompanyView from '@/views/CreateCompanyView.vue'
import CompanyUpdateView from '@/views/CompanyUpdateView.vue'
import TourCreateView from '@/views/TourCreateView.vue'
import ToursView from '@/views/ToursView.vue'
import TourView from '@/views/TourView.vue'
import TourUpdateView from '@/views/TourUpdateView.vue'
import ContractsView from '@/views/ContractsView.vue'

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
      path: '/home',
      name: 'home',
      component: HomePage,
      children: [
        {
          path: '/users/:id',
          name: 'user',
          props: true,
          component: UserView,
        },
        {
          path: '/users/:id/edit',
          name: 'user_update',
          props: true,
          component: UserUpdateView,
        },
        {
          path: '/categories',
          name: 'categories',
          component: CategoriesView,
        },
        {
          path: '/countries',
          name: 'counties',
          component: CountriesView,
        },
        {
          path: '/cities',
          name: 'cities',
          component: CitiesView,
        },
        {
          path: '/companies',
          name: 'companies',
          component: CompaniesView,
        },
        {
          path: '/create-company',
          name: 'create-company',
          component: CreateCompanyView,
        },
        {
          path: '/update-company/:id',
          name: 'update-company',
          props: true,
          component: CompanyUpdateView,
        },
        {
          path: '/create-tour',
          name: 'create-tour',
          component: TourCreateView,
        },
        {
          path: '/tours',
          name: 'tours',
          component: ToursView,
        },
        {
          path: '/tour/:id',
          props: true,
          name: 'tour',
          component: TourView,
        },
        {
          path: '/tour-update/:id',
          props: true,
          name: 'tour-update',
          component: TourUpdateView,
        },
        {
          path: '/contracts',
          name: 'contracts',
          component: ContractsView,
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

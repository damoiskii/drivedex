import { createRouter, createWebHistory } from 'vue-router'
import AppLayout from '../components/AppLayout.vue'
import DashboardPage from '../views/DashboardPage.vue'
import EmployeesPage from '../views/EmployeesPage.vue'
import DriversPage from '../views/DriversPage.vue'
import PassengersPage from '../views/PassengersPage.vue'
import RolesPage from '../views/RolesPage.vue'
import BusesPage from '../views/BusesPage.vue'
import LocationsPage from '../views/LocationsPage.vue'
import RoutesPage from '../views/RoutesPage.vue'
import EmailPage from '../views/EmailPage.vue'
import SettingsPage from '../views/SettingsPage.vue'
import NotificationsPage from '../views/NotificationsPage.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      component: AppLayout,
      children: [
        {
          path: '',
          redirect: '/dashboard'
        },
        {
          path: 'dashboard',
          name: 'dashboard',
          component: DashboardPage,
        },
        {
          path: 'employees',
          name: 'employees',
          component: EmployeesPage,
        },
        {
          path: 'drivers',
          name: 'drivers',
          component: DriversPage,
        },
        {
          path: 'passengers',
          name: 'passengers',
          component: PassengersPage,
        },
        {
          path: 'roles',
          name: 'roles',
          component: RolesPage,
        },
        {
          path: 'buses',
          name: 'buses',
          component: BusesPage,
        },
        {
          path: 'locations',
          name: 'locations',
          component: LocationsPage,
        },
        {
          path: 'routes',
          name: 'routes',
          component: RoutesPage,
        },
        {
          path: 'email',
          name: 'email',
          component: EmailPage,
        },
        {
          path: 'settings',
          name: 'settings',
          component: SettingsPage,
        },
        {
          path: 'notifications',
          name: 'notifications',
          component: NotificationsPage,
        },
      ]
    }
  ],
})

export default router

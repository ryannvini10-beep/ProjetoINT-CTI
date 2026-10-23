
import { createRouter, createWebHistory } from 'vue-router'

// Páginas públicas
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Cadastro from '../views/Cadastro.vue'

// Sidebar / layout
import Sidebar from '../components/Sidebar.vue'

// Páginas do sistema
import Upload from '../views/Upload.vue'
import Dashboard from '../views/Dashboard.vue'
import Relatorio from '../views/Relatorio.vue'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/cadastro',
    name: 'Cadastro',
    component: Cadastro
  },
  {
    path: '/app',
    component: Sidebar,
    redirect: '/app/upload',
    children: [
      {
        path: 'upload',
        name: 'Upload',
        component: Upload
      },
      {
        path: 'dashboard',
        name: 'Dashboard',
        component: Dashboard
      },
      {
        path: 'relatorio',
        name: 'Relatorio',
        component: Relatorio
      }
    ]
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router

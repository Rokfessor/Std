import { createRouter, createWebHistory } from 'vue-router'
import Game from '@/components/Game'
import About from '@/components/About'

const routes = [
  {
    path: '/',
    component: About
  },
  {
    path: '/game',
    component: Game
  },
  {
    path: '/',
    component: About
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL), routes
})

export default router
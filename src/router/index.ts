import { createRouter, createWebHistory } from 'vue-router';

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'main',
      component: () => import('@/layout/default-layout.vue'),
      children: [
        {
          path: 'home',
          name: 'home',
          component: () => import('@/pages/home/home-index.vue')
        },
        {
          path: 'discover',
          name: 'discover',
          component: () => import('@/pages/discover/discover-index.vue')
        }
      ]
    },
    {
      path: '/:pathMatch(.*)*',
      redirect: '/'
    }
  ]
});

export default router;

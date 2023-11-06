import { createRouter, createWebHistory } from 'vue-router';

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      redirect: '/home'
    },
    {
      children: [
        {
          path: 'home',
          name: 'home',
          component: () =>
            import('@/pages/home/home-index.vue')
        },
        {
          path: 'discover',
          name: 'discover',
          component: () =>
            import('@/pages/discover/discover-index.vue')
        },
        {
          path: 'user',
          name: 'user',
          component: () =>
            import('@/pages/user/user-index.vue')
        },
        {
          path: 'music',
          name: 'music',
          component: () =>
            import('@/pages/discover/music-index.vue')
        },
        {
          path: 'game',
          name: 'game',
          component: () =>
            import('@/pages/discover/game-index.vue')
        },
        {
          path: 'acg',
          name: 'acg',
          component: () =>
            import('@/pages/discover/acg-index.vue')
        },
        {
          path: 'panel',
          name: 'panel',
          component: () =>
            import('@/pages/discover/panel-index.vue')
        },
        {
          path: 'sport',
          name: 'sport',
          component: () =>
            import('@/pages/discover/sport-index.vue')
        },
        {
          path: 'hot',
          name: 'hot',
          component: () =>
            import('@/pages/discover/hot-index.vue')
        },
        {
          path: 'fun',
          name: 'fun',
          component: () =>
            import('@/pages/discover/fun-index.vue')
        }
      ],
      component: () =>
        import('@/layout/default-layout.vue'),
      name: 'main',
      path: '/'
    },
    {
      path: '/:pathMatch(.*)*',
      redirect: '/'
    }
  ]
});

export default router;

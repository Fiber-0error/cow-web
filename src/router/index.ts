import { createRouter, createWebHistory } from 'vue-router';

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      redirect: 'home/project'
    },
    {
      path: '/',
      name: 'main',
      component: () => import('@/layout/default-layout.vue'),
      children: [
        {
          path: 'home',
          name: 'home',
          component: () => import('@/layout/home-layout.vue'),
          children: [
            {
              path: ':project',
              name: 'project',
              component: () => import('@/pages/home/home-index.vue')
            }
          ]
        },
        {
          path: 'interface/:project',
          name: 'interface',
          component: () => import('@/layout/http-layout.vue'),
          children: [
            {
              path: 'http',
              name: 'http',
              component: () => import('@/pages/interface/http/http-index.vue'),
              children: [
                {
                  path: 'overview',
                  name: 'overview',
                  component: () => import('@/pages/interface/http/http-overview.vue')
                },
                {
                  path: 'new',
                  name: 'new',
                  component: () => import('@/pages/interface/http/http-new-request.vue')
                },
                {
                  path: 'directory',
                  name: 'directory',
                  component: () => import('@/pages/interface/http/http-root-directory.vue')
                },
                {
                  path: 'modal',
                  name: 'modal',
                  component: () => import('@/pages/interface/http/http-data-modal.vue')
                },
                {
                  path: 'quick',
                  name: 'quick',
                  component: () => import('@/pages/interface/http/http-quick-request.vue')
                },
                {
                  path: ':requestId',
                  name: 'requestId',
                  component: () => import('@/pages/interface/http/http-request.vue')
                }
              ]
            },
            {
              path: 'autotest',
              name: 'autotest',
              component: () => import('@/pages/interface/autotest/autotest-index.vue')
            },
            {
              path: 'setting',
              name: 'setting',
              component: () => import('@/pages/interface/setting/setting-index.vue')
            },
            {
              path: 'share',
              name: 'share',
              component: () => import('@/pages/interface/share/share-index.vue')
            }
          ]
        }
      ]
    }
  ]
});

export default router;

<script lang='ts' setup>
import {
  IconComputer,
  IconMindMapping,
  IconSettings,
  IconStorage
} from '@arco-design/web-vue/es/icon';
import router from '@/router';
import { computed } from 'vue';

const menuListTop = [
  {
    label: '首页',
    url: 'discover',
    icon: IconSettings
  },
  {
    label: '推荐',
    url: 'home',
    icon: IconStorage
  },
  // {
  //   label: '关注',
  //   url: 'follow',
  //   icon: IconStorage
  // },
  // {
  //   label: '朋友',
  //   url: 'friend',
  //   icon: IconMindMapping
  // },
  {
    label: '我的',
    url: 'user',
    icon: IconComputer
  }
];
const menuListBottom = [
  // {
  //   label: '直播',
  //   url: 'live',
  //   icon: IconSettings
  // },
  // {
  //   label: '放映厅',
  //   url: 'vs',
  //   icon: IconSettings
  // },
  {
    label: '知识',
    url: 'panel',
    icon: IconSettings
  },
  {
    label: '热点',
    url: 'hot',
    icon: IconSettings
  },
  {
    label: '游戏',
    url: 'game',
    icon: IconSettings
  },
  {
    label: '娱乐',
    url: 'fun',
    icon: IconSettings
  },
  {
    label: '二次元',
    url: 'acg',
    icon: IconSettings
  },
  {
    label: '音乐',
    url: 'music',
    icon: IconSettings
  }
];
const routerUrl = computed(() => router.currentRoute.value.fullPath);
const routerTo = (url) => {
  const replaceRoute = routerUrl.value.replace(
      /(.*)/,
      `${url}`
  );
  console.log(replaceRoute);
  router.replace(replaceRoute);
};
</script>

<template>
  <a-space direction='vertical' fill>
    <a-menu v-model:selected-keys='routerUrl' :default-selected-key='routerUrl'>
      <a-menu-item
          v-for='(item, index) in menuListTop'
          :key='item.url'
          @click='routerTo(item.url)'
      >
        <component :is='item.icon' />
        {{ item.label }}
      </a-menu-item>
      <div style='margin: 0 12px'>
        <a-divider />
      </div>
      <a-menu-item
          v-for='(item, index) in menuListBottom'
          :key='item.url'
          @click='routerTo(item.url)'
      >
        <component :is='item.icon' />
        {{ item.label }}
      </a-menu-item>
    </a-menu>
  </a-space>
</template>

<style lang='less' scoped></style>

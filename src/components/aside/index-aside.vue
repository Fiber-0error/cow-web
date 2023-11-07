<script lang='ts' setup>
import {
  IconBook,
  IconComputer, IconFire, IconHome,
  IconMindMapping, IconMusic, IconPlayArrow, IconSend,
  IconSettings, IconStar,
  IconStorage, IconTrophy, IconUser
} from '@arco-design/web-vue/es/icon';
import router from '@/router';
import { computed } from 'vue';

const menuListTop = [
  {
    label: '首页',
    url: 'discover',
    icon: IconHome
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
    icon: IconUser
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
    icon: IconBook
  },
  {
    label: '热点',
    url: 'hot',
    icon: IconFire
  },
  {
    label: '游戏',
    url: 'game',
    icon: IconTrophy
  },
  {
    label: '娱乐',
    url: 'fun',
    icon: IconStar
  },
  {
    label: '二次元',
    url: 'acg',
    icon: IconSend
  },
  {
    label: '音乐',
    url: 'music',
    icon: IconMusic
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

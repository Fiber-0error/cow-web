<script lang="ts" setup>
import {
  IconComputer,
  IconMindMapping,
  IconSettings,
  IconStorage
} from '@arco-design/web-vue/es/icon';
import router from '@/router';

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
  {
    label: '关注',
    url: 'follow',
    icon: IconStorage
  },
  {
    label: '朋友',
    url: 'friend',
    icon: IconMindMapping
  },
  {
    label: '我的',
    url: 'user',
    icon: IconComputer
  }
];
const menuListBottom = [
  {
    label: '直播',
    url: 'live',
    icon: IconSettings
  },
  {
    label: '放映厅',
    url: 'vs',
    icon: IconSettings
  },
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
const routerTo = (url) => {
  console.log(url);
  console.log(router.currentRoute.value.fullPath);
  const replaceRoute = router.currentRoute.value.fullPath.replace(
    /(.*)/,
    `${url}`
  );
  console.log(replaceRoute);
  router.replace(replaceRoute);
};
</script>

<template>
  <a-space direction="vertical" fill>
    <a-menu :default-selected-keys="['1']">
      <a-menu-item
        v-for="(item, index) in menuListTop"
        :key="`${index}`"
        @click="routerTo(item.url)"
      >
        <component :is="item.icon" />
        {{ item.label }}
      </a-menu-item>
      <div style="margin: 0 12px">
        <a-divider />
      </div>
      <a-menu-item
        v-for="(item, index) in menuListBottom"
        :key="`${index + menuListTop.length}`"
        @click="routerTo(item.url)"
      >
        <component :is="item.icon" />
        {{ item.label }}
      </a-menu-item>
    </a-menu>
  </a-space>
</template>

<style lang="less" scoped></style>

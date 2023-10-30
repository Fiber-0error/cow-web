<script lang="ts" setup>
import { IconMindMapping } from '@arco-design/web-vue/es/icon';
import router from '@/router';

const toHttpRootInterface = () => {
  const replaceRoute = router.currentRoute.value.fullPath.replace(
    /(.*?)(.)(http|autotest|setting|share)(.*)/,
    '$1/http/'
  );
  router.replace(replaceRoute);
};
const toHttpAutoTest = () => {
  const replaceRoute = router.currentRoute.value.fullPath.replace(
    /(.*?)(.)(http|autotest|setting|share)(.*)/,
    '$1/autotest/'
  );
  router.replace(replaceRoute);
};
const toProjectSetting = () => {
  const replaceRoute = router.currentRoute.value.fullPath.replace(
    /(.*?)(.)(http|autotest|setting|share)(.*)/,
    '$1/setting/'
  );
  router.replace(replaceRoute);
};
const toProjectShare = () => {
  const replaceRoute = router.currentRoute.value.fullPath.replace(
    /(.*?)(.)(http|autotest|setting|share)(.*)/,
    '$1/share/'
  );
  router.replace(replaceRoute);
};
</script>

<template>
  <a-menu :default-selected-keys="['0']">
    <a-menu-item key="0" @click="toHttpRootInterface">
      <icon-mind-mapping size="16" />
      接口管理
    </a-menu-item>
    <!--    <a-menu-item key="1" @click="toHttpAutoTest">-->
    <!--      <icon-storage size="16" />-->
    <!--      自动化测试-->
    <!--    </a-menu-item>-->
    <!--    <a-menu-item key="2" @click="toProjectSetting">-->
    <!--      <icon-settings size="16" />-->
    <!--      项目设置-->
    <!--    </a-menu-item>-->
    <!--    <a-menu-item key="3" @click="toProjectShare">-->
    <!--      <icon-computer size="16" />-->
    <!--      在线分享-->
    <!--    </a-menu-item>-->
  </a-menu>
</template>

<style lang="less" scoped></style>

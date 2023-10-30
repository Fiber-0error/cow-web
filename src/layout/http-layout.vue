<script lang="ts" setup>
import HttpAside from '@/components/aside/http-aside.vue';
</script>
<template>
  <a-layout>
    <a-layout-sider style="padding-top: 60px">
      <http-aside />
    </a-layout-sider>

    <a-layout style="padding-top: 60px">
      <router-view v-slot="{ Component }">
        <transition appear mode="out-in" name="fade">
          <component :is="Component" />
        </transition>
      </router-view>
    </a-layout>
  </a-layout>
</template>

<style lang="less" scoped></style>

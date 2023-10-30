<script lang="ts" setup>
import HomeAside from '@/components/aside/home-aside.vue';
</script>

<template>
  <a-layout>
    <a-layout-sider breakpoint="xl" collapsible style="padding-top: 60px">
      <home-aside />
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

<style scoped></style>

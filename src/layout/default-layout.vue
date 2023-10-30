<template>
  <a-layout style="height: 100vh">
    <a-layout-header class="layout-navbar">
      <nav-bar />
    </a-layout-header>
    <a-layout>
      <router-view v-slot="{ Component }">
        <transition appear mode="out-in" name="fade">
          <component :is="Component" />
        </transition>
      </router-view>
    </a-layout>
  </a-layout>
</template>

<script lang="ts" setup>
import NavBar from '@/components/navbar/navbar-index.vue';
</script>

<style lang="less" scoped>
@nav-size-height: 60px;
.layout-navbar {
  position: fixed;
  top: 0;
  left: 0;
  z-index: 100;
  width: 100%;
  height: @nav-size-height;
}
</style>

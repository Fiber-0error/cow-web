<template>
  <a-layout style='height: 100vh'>
    <a-layout-sider :width='160' collapsible style='padding-top: 60px'>
      <main-aside />
    </a-layout-sider>
    <a-layout style='height:100vh;overflow: hidden' class='default-color'>
      <a-layout-header class='layout-navbar default-color'>
        <nav-bar />
      </a-layout-header>
      <a-layout class='default-color-less'>
        <router-view v-slot='{ Component }' >
          <transition appear mode='out-in' name='fade' >
            <component :is='Component' />
          </transition>
        </router-view>
      </a-layout>
    </a-layout>
  </a-layout>
</template>

<script lang='ts' setup>
import NavBar from '@/components/navbar/index-navbar.vue';
import MainAside from '@/components/aside/index-aside.vue';
</script>

<style lang='less' scoped>
@nav-size-height: 60px;
.layout-navbar {
}
</style>

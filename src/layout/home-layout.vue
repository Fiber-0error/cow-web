<script lang="ts" setup></script>

<template>
  <a-layout>
    <a-layout style="margin: 0 24px; overflow: hidden">
      <a-layout-content>
        <router-view v-slot="{ Component }">
          <transition appear mode="out-in" name="fade">
            <component :is="Component" />
          </transition>
        </router-view>
      </a-layout-content>
    </a-layout>
  </a-layout>
</template>

<style scoped></style>

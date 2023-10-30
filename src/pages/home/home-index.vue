<script lang="ts" setup>
// Import Swiper styles
import "swiper/css";

import { Swiper, SwiperSlide } from "swiper/vue";
</script>

<template>
  <a-layout style="margin: 0 24px">
    <a-layout-header>
      <a-page-header :show-back="false" :style="{ background: 'var(--color-bg-2)' }" title="搜索栏">
        <template #subtitle>
          <a-space>
            <a-tag color="orange" size="small">{{ "选项卡" }}</a-tag>
          </a-space>
        </template>
      </a-page-header>
    </a-layout-header>
    <a-layout-content>
      <swiper
        direction="vertical"
        style="height: calc(100vh - 144px)">
        <template v-for="item in 10">
          <swiper-slide style="height: 100%;border: dodgerblue 5px dashed;box-sizing: border-box">{{ "Slide" + item }}
          </swiper-slide>
        </template>
      </swiper>
    </a-layout-content>
  </a-layout>
</template>

<style scoped>
h1 {
  margin: 0;
  text-align: center;
}


</style>

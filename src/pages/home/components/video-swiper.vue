<script lang="ts" setup>
// Import Swiper styles
import 'swiper/css';

import { Swiper, SwiperSlide } from 'swiper/vue';
import VideoInner from '@/pages/home/components/video-inner.vue';
</script>

<template>
  <swiper direction="vertical" style="height: calc(100vh - 144px)">
    <template v-for="item in 10">
      <swiper-slide style="border: dodgerblue 5px dashed">
        {{ 'Slide' + item }}
        <video-inner />
      </swiper-slide>
    </template>
  </swiper>
</template>

<style scoped></style>

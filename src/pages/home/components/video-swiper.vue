<script lang="ts" setup>
// Import Swiper styles
import 'swiper/css';
import {Swiper, SwiperSlide} from 'swiper/vue';

import 'swiper/css/bundle';
import VideoInner from '@/pages/home/components/video-inner.vue';
import {Keyboard, Pagination, Navigation, Mousewheel} from 'swiper/modules';

const modules = [Keyboard, Pagination, Navigation, Mousewheel]

const onSwiper = (swiper) => {
  console.log(swiper)
}
const slideChange = (slideChange) => {
  console.log(slideChange)
}
const reachEnd = (reachEnd) => {
  console.log(reachEnd)
}
</script>

<template>
  <swiper :keyboard="{enabled: true,}"
          :modules="modules"
          :mousewheel="true"
          :pagination="{clickable: true,}"
          direction="vertical"
          style="height: calc(100vh - 144px)"
          @reachEnd="reachEnd"
          @slideChange="slideChange"
          @swiper="onSwiper">
    <swiper-slide v-for="item in 10" v-slot="{ isActive,isVisible,isNext }" style="border: dodgerblue 5px dashed">
      {{ isActive }}
      {{ isVisible }}
      {{ isNext }}
      {{ 'Slide' + item }}
      <video-inner/>
    </swiper-slide>
  </swiper>
</template>

<style scoped></style>

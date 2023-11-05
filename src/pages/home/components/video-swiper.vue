<script lang="ts" setup>
import 'swiper/css';
import {Swiper, SwiperSlide} from 'swiper/vue';
import {Keyboard, Pagination, Navigation, Mousewheel} from 'swiper/modules';
import {getVideoNext} from "@/http/api/video";
import 'swiper/css/bundle';
import VideoInner from '@/pages/home/components/video-inner.vue';
import VideoRight from '@/pages/home/components/video-right.vue';
import VideoInfo from '@/pages/home/components/video-info.vue';
import { ref , onMounted} from 'vue';

const modules = [Keyboard, Pagination]

const onSwiper = (swiper) => {
  // console.log(swiper)
}
const slideChange = (slideChange) => {
  // console.log(slideChange)
}
const reachEnd = (reachEnd) => {
  console.log('reachEnd')
  getVideo();
}
const getVideo = () => {
  getVideoNext().then(r => {
    console.log(r)
    videoData.value = [...videoData.value, ...r.data];
  })
}
getVideo();
const videoData = ref<any[]>([]);

</script>

<template>
   <swiper :keyboard="{enabled: true,}"
          :modules="modules"
          direction="vertical"
          style="height: calc(100vh - 144px)"
          :noSwipingClass='"xgplayer-progress"'
          @reachEnd="reachEnd"
          @slideChange="slideChange"
          @swiper="onSwiper">
    <swiper-slide

    v-for="item in videoData" :key="item"
    v-slot="{ isActive,isVisible,isNext }"
    style="z-index: 2;"
       >
      <video-inner :videoItem="item" :url="item" :isActive="isActive" :isVisible="isVisible" />
      <video-info :videoItem="item" />
      <video-right :videoItem="item" />
    </swiper-slide>
  </swiper>
</template>

<style scoped></style>

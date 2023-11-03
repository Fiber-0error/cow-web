<script lang="ts" setup>
import 'swiper/css';
import {Swiper, SwiperSlide} from 'swiper/vue';
import {Keyboard, Pagination, Navigation, Mousewheel} from 'swiper/modules';
import {getVideoNext} from "@/http/api/video";
import 'swiper/css/bundle';
import VideoInner from '@/pages/home/components/video-inner.vue';
import { ref , onMounted} from 'vue';

const modules = [Keyboard, Pagination]

const onSwiper = (swiper) => {
  console.log(swiper)
}
const slideChange = (slideChange) => {
  console.log(slideChange)
}
const reachEnd = (reachEnd) => {
  console.log(reachEnd)
}
const init = () => {
  getVideoNext().then(r => {
    console.log(r)
  })
}
// init()

const swiperRef = ref();

const urls = ref([
  'https://www.w3schools.com/html/movie.mp4',
  'https://media.w3.org/2010/05/sintel/trailer.mp4',
  'http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4',
  'https://www.w3school.com.cn/example/html5/mov_bbb.mp4',
  'https://stream7.iqilu.com/10339/upload_transcode/202002/18/20200218114723HDu3hhxqIT.mp4',
  'https://stream7.iqilu.com/10339/upload_transcode/202002/18/20200218093206z8V1JuPlpe.mp4'
])

onMounted(() => {
  setTimeout(() => {
    urls.value = [...urls.value ,'https://stream7.iqilu.com/10339/upload_transcode/202002/17/20200217104524H4D6lmByOe.mp4']
    console.log('urls', urls);
  }, 3000);
})
</script>

<template>
  <swiper :keyboard="{enabled: true,}"
          :modules="modules"
          direction="vertical"
          style="height: calc(100vh - 144px)"
          @reachEnd="reachEnd"
          @slideChange="slideChange"
          @swiper="onSwiper">
    <swiper-slide v-for="item in urls" :key="item" v-slot="{ isActive,isVisible,isNext }" style="border: dodgerblue 5px dashed">
      <video-inner :url="item" :isActive="isActive" :isVisible="isVisible" />
    </swiper-slide>
  </swiper>
</template>

<style scoped></style>

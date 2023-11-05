<script lang="ts" setup>
import 'swiper/css';
import {Swiper, SwiperSlide} from 'swiper/vue';
import {Keyboard, Pagination, Navigation, Mousewheel} from 'swiper/modules';
import {getVideoNext, userWatchVideo} from "@/http/api/video";
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
  // console.log(slideChange);

}
const reachEnd = (reachEnd) => {
  console.log('reachEnd')
  getVideo()
}
const getVideo = async () => {
  const initArr = await getVideoNext();
  const resArr = initArr.data.map(item => {
    return userWatchVideo(item.id);
  })
  const resData = await Promise.all(resArr);
  const list = resData.map(item => item.data);
  videoData.value = [...videoData.value, ...list];
  console.log('vide', videoData.value);
  // videoData.value = [...videoData.value, ...res.data];
}

const changeData = ( data) => {
  const avideoData = videoData.value;
  const [index] = avideoData.filter(item => item.id === data.id);
  avideoData[index] = {...videoData[index], ...data};
  console.log(data, avideoData[index] );
}
onMounted(() => {
  getVideo();
})
const videoData = ref<any[]>([]);

</script>

<template>
   <swiper
          :keyboard="{enabled: true,}"
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
       >
      <video-inner :videoItem="item" :url="item" :isActive="isActive" :isVisible="isVisible" @changeData="changeData" />
      <video-info :videoItem="item" />
      <video-right :videoItem="item" @changeData="changeData" />
    </swiper-slide>
  </swiper>
</template>

<style scoped></style>

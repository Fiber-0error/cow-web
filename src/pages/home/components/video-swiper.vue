<script lang='ts' setup>
import 'swiper/css';
import { Swiper, SwiperSlide } from 'swiper/vue';
import { Keyboard, Pagination, Navigation, Mousewheel } from 'swiper/modules';
import { getVideoNext, userWatchVideo } from '@/http/api/video';
import 'swiper/css/bundle';
import VideoInner from '@/pages/home/components/video-inner.vue';
import VideoRight from '@/pages/home/components/video-right.vue';
import VideoInfo from '@/pages/home/components/video-info.vue';
import { ref , onMounted} from 'vue';
import { useUserStore } from '@/stores/user';
import { useVideoStore } from '@/stores/video';

const isFirst = ref(true);
const user = useUserStore();
const videoStore = useVideoStore();

const modules = [Keyboard, Pagination];

const onSwiper = (swiper) => {
  console.log('swiper', swiper)
};
const slideChange = (slideChange) => {
  const {previousIndex, activeIndex} = slideChange;
  if(previousIndex < activeIndex) {
    videoStore.watchVideo(videoStore.videoList[activeIndex].id)
  }
};
const reachEnd = (reachEnd) => {
  getVideo();
};
const getVideo = async () => {
  const initArr = await getVideoNext();
  videoStore.pushVideoList(initArr.data);
  console.log('isFirst', isFirst.value);
  if (isFirst.value) {
    await videoStore.watchVideo(initArr.data[0].id);
  }
};

const changeData = (data) => {
  const avideoData = videoData.value;
  const [index] = avideoData.filter(item => item.id === data.id);
  avideoData[index] = { ...videoData[index], ...data };
  console.log(data, avideoData[index]);
};
onMounted(() => {
  getVideo().then(() => {
    isFirst.value = false;
  })
});
</script>

<template>
  <swiper
    :keyboard='{enabled: true,}'
    :modules='modules'
    :noSwipingClass='"xgplayer-progress"'
    direction='vertical'
    style='height: calc(100vh - 108px)'
    @reachEnd='reachEnd'
    @slideChange='slideChange'
    @swiper='onSwiper'>
    <swiper-slide
      v-for='item in videoStore.videoList' :key='item'
      v-slot='{ isActive,isVisible,isNext }'
    >
      <video-inner :isActive='isActive' :isVisible='isVisible' :url='item' :videoItem='item' @changeData='changeData' />
      <video-info :videoItem='item' />
      <video-right :videoItem='item' @changeData='changeData' />
    </swiper-slide>
  </swiper>
</template>

<style scoped></style>

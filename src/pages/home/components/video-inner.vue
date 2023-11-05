<template>
    <div class="my-video" ref='videoRef'></div>
</template>
<script setup lang='ts'>
import {onMounted, ref , watch, computed} from 'vue';
import {baseVideoConfig, getVideoHeightWidth ,usePlayer} from '@/config/video'
const props = defineProps(['url', 'isActive', 'videoItem'])
const videoRef = ref();
let videoPalyer;
onMounted( () => {
    videoPalyer = usePlayer(videoRef.value, props.videoItem.url);
})

const isActive = computed(() => {
  return props.isActive;
})

watch(isActive, (newVal, oldVal) => {
  if (newVal) {
      videoPalyer.play();
  } else {
      videoPalyer.pause();
  }
})


</script>
<style lang='scss' scoped>
</style>

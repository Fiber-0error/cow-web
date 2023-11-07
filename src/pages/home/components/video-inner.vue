<template>
    <div class="my-video" ref='videoRef'></div>
</template>
<script setup lang='ts'>
import {onMounted, ref , watch, computed} from 'vue';
import {usePlayer} from '@/config/video';
import {Events } from 'xgplayer';
import { updateVideoWatchProgress } from '@/http/api/video';

const props = defineProps(['url', 'isActive', 'videoItem'])
const {url,id} =  props.videoItem
const videoRef = ref();
let videoPalyer;
onMounted( () => {
    videoPalyer = usePlayer(videoRef.value, url);

     // 通过player调用
  videoPalyer.on(Events.PLAY, async () => {
  })

  videoPalyer.on(Events.PAUSE, (videoPalyer) => {
    // 调用视频上传进度接口
    console.log('video is pause', videoPalyer)
    updateVideoWatchProgress(id, videoPalyer.currentTime.toFixed(0));
  })

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

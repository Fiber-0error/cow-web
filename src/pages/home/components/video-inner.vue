<template>
    <div ref='videoRef'></div>
</template>
<script setup lang='ts'>
import Player from 'xgplayer';
import {onMounted, ref , defineProps, watch, computed} from 'vue';
import {baseVideoConfig, getVideoHeightWidth} from '@/config/video'

const props = defineProps(['url', 'isActive'])
const videoRef = ref(null)
let videoPalyer;
onMounted(() => {
  console.log('url', props)
  videoPalyer = new Player({
    el: videoRef.value,
    url: props.url,
    width: getVideoHeightWidth().width - 10,
    height: getVideoHeightWidth().height - 10,
    ...baseVideoConfig
  })
})

const isActive = computed(() => {
  return props.isActive;
})

watch(isActive, (newVal, oldVal) => {
  console.log('val', newVal, oldVal, videoPalyer);
  if (newVal) {
      videoPalyer.play();
  } else {
      videoPalyer.pause();
  }
})


</script>
<style lang='scss' scoped>
</style>

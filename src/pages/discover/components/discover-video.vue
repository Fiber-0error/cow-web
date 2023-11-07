<script lang='ts' setup>
import { usePlayer } from '@/config/video';
import { computed, ref, onMounted } from 'vue';
const {videoInfo} = defineProps({
  videoInfo: Object,
  default: () => ({
    id: '1',
    name: '12',
    tag: '321',
    type: '游戏'
  })
});
const randomHeight = computed(() => Math.floor(Math.random() * 300 + 150));
const videoRef = ref()
let player: any;
onMounted(() => {
   player = usePlayer(videoRef.value, videoInfo.url, document.querySelector('.card')?.getBoundingClientRect().width , randomHeight, false);
  })

  const handleMouseOver = () => {
    if(!player) return;
    player.play();
  }

  const handleMouseLeave = () => {
    if(!player) return;
    player.pause();

  }
const emit = defineEmits(['toHome'])
  const videoClick = () => {
    emit("toHome")
  }
</script>

<template>
  <div @click='videoClick()' class='defaultVideo' :style='{height: randomHeight+"px"}' ref="videoRef" @mouseover="handleMouseOver" @mouseleave="handleMouseLeave" >
  </div>
</template>

<style scoped>

</style>

<script lang='ts' setup>
import { usePlayer } from '@/config/video';
import { computed, ref, onMounted } from 'vue';

const randomHeight = computed(() => Math.floor(Math.random() * 400 + 150));
const videoRef = ref()
let player: any;
onMounted(() => {
   player = usePlayer(videoRef.value, '这里放url', document.querySelector('.card')?.getBoundingClientRect().width , randomHeight, false);
  })

  const handleMouseOver = () => {
    if(!player) return;
    player.play();
  }

  const handleMouseLeave = () => {
    if(!player) return;
    player.pause();

  }
</script>

<template>
  <div :style='{height: randomHeight+"px"}' ref="videoRef" @mouseover="handleMouseOver" @mouseleave="handleMouseLeave" >
  </div>
</template>

<style scoped>

</style>

<script lang='ts' setup>
import DiscoverVideo from '@/pages/discover/components/discover-video.vue';
</script>

<template>
  <div class='card' style='width: 300px;border: 1px dodgerblue solid;border-radius: 8px;margin: 8px'>
    <div>
      video
      <discover-video />
    </div>
    <div style='height: 50px;background-color: rgba(128,128,128,0.3)'>
      video-info
      <!--              {{ masonryColumnList }}-->
    </div>
  </div>
</template>

<style scoped>

</style>

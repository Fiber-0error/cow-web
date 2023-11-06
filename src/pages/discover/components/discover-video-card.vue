<script lang='ts' setup>
import DiscoverVideo from '@/pages/discover/components/discover-video.vue';
import router from '@/router';
import { computed } from 'vue';
import { IconSettings } from '@arco-design/web-vue/es/icon';

const {} = defineProps({
  videoInfo: Object,
  default: () => ({
    id: '1',
    name: '12',
    tag: '321',
    type: '游戏'
  })
});

const typeMap = {
  '直播': 'live',
  '放映厅': 'vs',
  '知识': 'panel',
  '体育': 'sport',
  '热点': 'hot',
  '游戏': 'game',
  '娱乐': 'fun',
  '二次元': 'acg',
  '音乐': 'music'
};
const routerTo = (url, id) => {
  const routerUrl = computed(() => router.currentRoute.value.fullPath);
  const replaceRoute = routerUrl.value.replace(
    /(.*)/,
    `${url}`
  );
  router.replace({
    path: replaceRoute, query: {
      id: id
    }
  });
};
</script>

<template>
  <div class='card' style='width: 300px;border: 1px dodgerblue solid;border-radius: 8px;margin: 8px'
       @click='routerTo("home",videoInfo.id)'>
    <div>
      video
      <discover-video />
    </div>
    <div style='height: 50px;background-color: rgba(128,128,128,0.3)'>
      {{ videoInfo.id }}
      {{ videoInfo.name }}
      {{ videoInfo.tag }}
      {{ videoInfo.type }}
      {{ videoInfo.totalTime }}
      {{ videoInfo.uploadUser }}
      {{ videoInfo.url }}
      <!--              {{ masonryColumnList }}-->
    </div>
  </div>
</template>

<style scoped>

</style>

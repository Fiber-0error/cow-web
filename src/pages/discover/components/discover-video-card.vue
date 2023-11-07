<script lang="ts" setup>
import DiscoverVideo from '@/pages/discover/components/discover-video.vue';
import router from '@/router';
import { useVideoStore } from '@/stores/video';
import { computed } from 'vue';

const { videoInfo, data } = defineProps({
  videoInfo: Object,
  data: Array,
  default: () => ({
    id: '1',
    name: '12',
    tag: '321',
    type: '游戏'
  })
});
const videoStore = useVideoStore();
const typeMap = {
  直播: 'live',
  放映厅: 'vs',
  知识: 'panel',
  体育: 'sport',
  热点: 'hot',
  游戏: 'game',
  娱乐: 'fun',
  二次元: 'acg',
  音乐: 'music'
};
const routerTo = (url, id) => {
  const routerUrl = computed(
    () => router.currentRoute.value.fullPath
  );
  const replaceRoute = routerUrl.value.replace(
    /(.*)/,
    `${url}`
  );
  console.log('data', data);

  const newData = JSON.parse(JSON.stringify(data));
  console.log('newData', newData);

  const currentItemIndex = newData.findIndex(
    (item) => item.id === id
  );
  const currentItem = newData.splice(currentItemIndex, 1);
  newData.unshift(currentItem[0]);
  videoStore.setVideoList(newData);
  router.replace({
    path: replaceRoute,
    query: {
      id: id
    },
    params: {
      videoArr: newData
    }
  });
};
</script>

<template>
  <div
    class="card"
    style="
      width: 300px;
      border: 1px rgba(170, 178, 189, 0.3) solid;
      border-radius: 8px;
      margin: 8px;
      overflow: hidden;
    "
    @click="routerTo('home', videoInfo.id)"
  >
    <div>
      <discover-video
        :videoInfo="videoInfo"
        @to-home="routerTo('home', videoInfo.id)"
      />
    </div>
    <div
      style="
        padding: 12px;
        background-color: rgba(128, 128, 128, 0.3);
      "
    >
      <div style="color: white">
        {{ videoInfo.name }}
      </div>
      <div style="color: rgba(166, 166, 166, 0.7)">
        @ {{ videoInfo.uploadUser }} ·
        {{ videoInfo.tag }}
        {{ videoInfo.type }}
      </div>
      <!--      {{ videoInfo.id }}-->
      <!--      {{ videoInfo.totalTime }}-->
      <!--      {{ videoInfo.url }}-->
      <!--              {{ masonryColumnList }}-->
    </div>
  </div>
</template>

<style scoped></style>

import { computed } from 'vue';

import { searchVideo } from '@/http/api/video';
import router from '@/router';

const typeMap = {
  '/discover': '',
  '/live': '直播',
  '/vs': '放映厅',
  '/panel': '知识',
  '/sport': '体育',
  '/hot': '热点',
  '/game': '游戏',
  '/fun': '娱乐',
  '/acg': '二次元',
  '/music': '音乐'
};
const routerUrl = computed(
  () => router.currentRoute.value.fullPath
);

async function getVideos() {
  let VideoData: any[] = [];
  await searchVideo(
    '',
    1,
    typeMap[routerUrl.value],
    14
  ).then(({ data: { records } }) => {
    VideoData = records;
  });
  return VideoData;
}

export { getVideos };

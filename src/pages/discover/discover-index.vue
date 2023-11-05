<script lang='ts' setup>
import { nextTick, onMounted, reactive, ref } from 'vue';
import { getFakeUrl } from '@/utils/common';
import { vReSize } from '@/utils/sizeDirect.js';
import DiscoverVideo from '@/pages/discover/components/discover-video.vue';
import DiscoverVideoBox from '@/pages/discover/components/discover-video-box.vue';

const data = getFakeUrl();
const columns = ref(null);
const masonryColumnList: any = reactive([]);

const findMinColumnIndex = (columnsEl: any) => {
  let MinColumnIndex = 0;
  columnsEl.value.reduce((now: any, next: any, index: number) => {
    if (next['offsetHeight'] < now['offsetHeight']) {
      MinColumnIndex = index;
      return next;
    } else {
      return now;
    }
  });
  return MinColumnIndex;
};
const initMasonry = ({ currentColumns }: { currentColumns: any }) => {
  for (let index = 0; index < currentColumns; index++) {
    masonryColumnList.push(ref([]));
  }
  for (let i = 0; i < data.length; i++) {
    let addColumnsIndex = findMinColumnIndex(columns);
    masonryColumnList[addColumnsIndex].value.push(data[i]);
  }
};

onMounted(() => {
  initMasonry({ currentColumns: columnsNum.value });
});

const columnsNum = ref(5);
const resize = ({ width, height }: { width: number, height: number }) => {
  let currentColumns = (width / 320) | 0;
  if (columnsNum.value !== currentColumns) {
    columnsNum.value = currentColumns;
    masonryColumnList.splice(0, masonryColumnList.length);
    console.log(masonryColumnList);
    initMasonry({ currentColumns: currentColumns });
  }
};
</script>

<template>
  <div>
    <div ref='masonry' v-re-size='resize' class='masonry'>
      <div v-for='column in columnsNum' ref='columns' class='column'>
        {{ column - 1 }}
        {{ masonryColumnList.value }}
        <discover-video-box v-for='(item,index) in masonryColumnList[column-1]' :key='index'>
          video
          <discover-video />
          <template #content>
            video-info
            {{item}}
          </template>
        </discover-video-box>
        <div v-for='(item,index) in masonryColumnList[column-1]' :key='index'>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.masonry {
  display: flex;
  flex-direction: row;
  justify-content: center;
}

.column {
  display: flex;
  flex-direction: column;
  height: fit-content;
}

</style>

<script lang='ts' setup>
import { nextTick, onMounted, ref } from 'vue';
import { getFakeUrl, getVideos } from '@/utils/common';
import { vReSize } from '@/utils/sizeDirect.js';
import DiscoverVideoCard from '@/pages/discover/components/discover-video-card.vue';

const data = ref([]);
const columns = ref(null);
const videos = ref(null);
const columnsNum = ref(5);
const render = ref(true);
const scroll = ref(null);
const scrollHeight = ref(null);
const masonryColumnList: any = ref([]);


const initMasonry = ({ ColumnNum }: { ColumnNum: any }) => {
  masonryColumnList.value.splice(0, masonryColumnList.value.length);
  for (let index = 0; index < ColumnNum; index++) {
    masonryColumnList.value.push({ height: 0, list: [] });
  }
};
const findMinColumnIndex = (columnsEl: any) => {
  let MinColumnIndex = 0;
  MinColumnIndex = masonryColumnList.value.map((item: any) => item.height)
      .reduce((now: number, next: number, index: number, arr: any) => next < arr[now] ? index : now, 0);
  return MinColumnIndex;
};
const initVideos = () => {
  for (let i = 0; i < data.value.length; i++) {
    masonryColumnList.value[findMinColumnIndex(columns)].list.push(data.value[i]);
    masonryColumnList.value[findMinColumnIndex(columns)].height += 1;
  }
};
const resetColumns = ({ columnsList, ColumnNum }: { columns: number, ColumnNum: number }) => {
  nextTick(() => {
    const { Column: minColumn, ColumnIndex: minColumnIndex } = columnsList.reduce(
        (now, next, index, arr) => {
          if (now.Column.offsetHeight > next.offsetHeight) {
            return {
              Column: next,
              ColumnIndex: index
            };
          } else {
            return {
              Column: now.Column,
              ColumnIndex: now.ColumnIndex
            };
          }
        }, {
          Column: { offsetHeight: 9999 },
          ColumnIndex: 0
        });
    const { Column: maxColumn, ColumnIndex: maxColumnIndex } = columnsList.reduce(
        (now, next, index, arr) => {
          if (now.Column.offsetHeight < next.offsetHeight) {
            return {
              Column: next,
              ColumnIndex: index
            };
          } else {
            return {
              Column: now.Column,
              ColumnIndex: now.ColumnIndex
            };
          }
        }, {
          Column: { offsetHeight: 0 },
          ColumnIndex: 0
        });
    console.log(minColumnIndex, maxColumnIndex);

    if (minColumn.offsetHeight < maxColumn.children[maxColumn.children.length - 1].offsetTop) {
      console.log('yes');
      masonryColumnList.value[minColumnIndex].list.push(
          masonryColumnList.value[maxColumnIndex].list.pop());
    }
    masonryColumnList.value.forEach((item) => item.height = 0);
    console.log(masonryColumnList.value);
  });
};
const resize = ({ width, height }: { width: number, height: number }) => {
  let ColumnNum = (width / 300) | 0;
  if (columnsNum.value !== ColumnNum) {
    columnsNum.value = ColumnNum;
    initMasonry({ ColumnNum: ColumnNum });
    initVideos();
    resetColumns({ columnsList: columns.value, ColumnNum: columnsNum.value });
  }
};

const addVideos = () => {
  getVideos()
      .then((r) => {
        data.value.push(...r);
        initVideos();
        resetColumns({ columnsList: columns.value, ColumnNum: columnsNum.value });
      });
};

onMounted(() => {
  initMasonry({ ColumnNum: columnsNum.value });
  addVideos();
});
const loading = ref(false);
const scrollEnd = () => {
  if (!loading.value) {
    if (scrollHeight.value.offsetHeight >= (scroll.value.getBoundingClientRect().top - 64)) {
      loading.value = true;
      setTimeout(() => {
        addVideos();
        loading.value = false;
      }, 500);
    }
  }
};

</script>

<template>
  <div ref='scrollHeight'>
    <a-scrollbar style='height:calc(100vh - 64px);overflow: auto;' @scroll='scrollEnd'>
      <div v-re-size='resize' class='masonry'>
        <div v-for='column in columnsNum' ref='columns' class='column'>
          <template v-if='masonryColumnList.length'>
            <template v-for='(item,index) in masonryColumnList[column-1]["list"]'>
              <discover-video-card ref='videos' :video-info='item' />
            </template>
          </template>
        </div>
      </div>
      <div ref='scroll'>
        <div style='display:flex;justify-content: center;align-items: center;height: 150px'>
          <icon-loading />
        </div>
      </div>
    </a-scrollbar>
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

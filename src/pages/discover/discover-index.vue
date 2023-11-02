<script lang="ts" setup>
import {onMounted, ref} from "vue";
import {getFakeUrl} from "@/utils/common"

import {debounce} from "@arco-design/web-vue/es/_utils/debounce";

const masonryColumns = ref(6)

const isResize = ref(true)

onMounted(() => {
  let debounced = debounce(function () {
    masonryColumns.value = (document.querySelector('#masonry')).style.cssText.replace(/.*repeat\((.).*/, `$1`)
    isResize.value = false
    setTimeout(() => {
      isResize.value = true
    }, 1000)
  }, 300);
  window.addEventListener('resize', debounced);
})

const data = getFakeUrl()
console.log(data)
let data1 = [], //第一列
    data2 = [], //第二列
    data3 = []; //第三列
const init = () => {
  let i = 0;
  while (i < data.length) {
    data1.push(data[i++]);
    if (i < data.length) {
      data2.push(data[i++]);
    }
    if (i < data.length) {
      data3.push(data[i++]);
    }
  }
}

</script>

<template>
  <div>
    <div class="masonry">
      <div class="column" style="width: 250px;border: 1px dodgerblue solid;border-radius: 8px;margin: 8px">
        <div style="height: 500px;">

        </div>
        <div style="height: 50px;background-color: rgba(128,128,128,0.3)">
          <!--          {{ i }}-->
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.masonry {
  display: flex;
  flex-direction: row;
}

.column {
  display: flex;
  flex-direction: column;
}

</style>

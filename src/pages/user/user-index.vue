<script lang="ts" setup>
import { userCollectList } from '@/http/api/login';
import { onMounted, ref } from 'vue';
import UserInfo from '@/pages/user/components/user-info.vue';

const userCollect = ref({});
onMounted(() => {
  userCollectList().then((r) => {
    console.log(r);
    userCollect.value = r.data;
  });
});
</script>

<template>
  <div style="background-color: #2c3e50">
    <div
      style="width: 70%; min-height: 100vh; margin: auto"
    >
      <user-info></user-info>
      <a-tabs default-active-key="2">
        <a-tab-pane key="1" title="作品 0">
          Content of Tab Panel 1
        </a-tab-pane>
        <a-tab-pane key="2" :title="'喜欢 ' + userCollect.length">
          <div style="color: white">
            {{ userCollect }}
          </div>
        </a-tab-pane>
        <a-tab-pane key="3">
          <template #title>收藏 </template>
          Content of Tab Panel 3
        </a-tab-pane>
      </a-tabs>
    </div>
  </div>
</template>

<style scoped></style>

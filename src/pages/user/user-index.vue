<script lang="ts" setup>
import {
  userCollectList,
  userLikeList
} from '@/http/api/login';
import { onMounted, ref } from 'vue';
import UserInfo from '@/pages/user/components/user-info.vue';
import DiscoverVideoCard from '@/pages/discover/components/discover-video-card.vue';
import { getUserList } from '@/http/api/user';

const userCollect = ref({});
const userLikes = ref({});
const userList = ref({});
onMounted(() => {
  userCollectList().then((r) => {
    console.log(r);
    userCollect.value = r.data;
  });
  userLikeList().then((r) => {
    console.log(r);
    userLikes.value = r.data;
  });
  getUserList().then((r) => {
    console.log(r);
    userList.value = r.data.records.splice(0,7);
  });
});
</script>

<template>
  <a-scrollbar style="overflow: auto">
    <div style="background-color: #2c3e50">
      <div
        style="width: 70%; min-height: 100vh; margin: auto"
      >
        <user-info></user-info>
        <a-tabs default-active-key="2">
          <a-tab-pane key="1">
            <template #title>
              作品 {{ userList.length }}
            </template>
            <div
              style="
                color: white;
                display: flex;
                flex-wrap: wrap;
              "
            >
              <div v-for="item in userList">
                <discover-video-card
                  ref="videos"
                  :video-info="item"
                />
              </div>
            </div>
          </a-tab-pane>
          <a-tab-pane key="2">
            <template #title
              >喜欢 {{ userLikes.length }}
            </template>
            <div
              style="
                color: white;
                display: flex;
                flex-wrap: wrap;
              "
            >
              <div v-for="item in userLikes">
                <discover-video-card
                  ref="videos"
                  :video-info="item.videoInfoPO"
                />
              </div>
            </div>
          </a-tab-pane>
          <a-tab-pane key="3">
            <template #title
              >收藏 {{ userCollect.length }}
            </template>
            <div style="color: white; display: flex">
              <div v-for="item in userCollect">
                <discover-video-card
                  ref="videos"
                  :video-info="item.videoInfoPO"
                />
              </div>
            </div>
          </a-tab-pane>
        </a-tabs>
      </div>
    </div>
  </a-scrollbar>
</template>

<style scoped>
:deep(.defaultVideo) {
  height: 230px !important;
}
</style>

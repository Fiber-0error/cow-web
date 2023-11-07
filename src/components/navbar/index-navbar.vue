<script lang="ts" setup>
import { IconSearch } from '@arco-design/web-vue/es/icon';
import { onMounted, ref } from 'vue';
import { useUserStore } from '@/stores/user';
import { hotKeyWord, searchVideo } from '@/http/api/video';

const user = useUserStore();

const searchLoading = ref(false);
const loginModal = ref();
const uploadModal = ref();
const handleClick = () => {
  loginModal.value.handleClick();
};

const handleClickUpload = () => {
  uploadModal.value.handleClick();
};
const KeyWord = ref([]);

const search = (value) => {
  searchLoading.value=true
  searchVideo(value,0,"",99).then((r) => {
    console.log(r);
    setTimeout(()=>{
      searchLoading.value=false
    },5000)
  });
};
onMounted(() => {
  hotKeyWord().then((r) => {
    KeyWord.value = r.data.splice(0, 8);
  });
});
</script>

<template>
  <div style="display: flex">
    <div style="flex: 1">
      <div
        style="
          height: 64px;
          text-align: center;
          width: 30%;
          margin: auto;
          line-height: 64px;
        "
      >
        <a-trigger
          :click-to-close="false"
          :render-to-body="false"
          :trigger="['click']"
        >
          <a-input-search
            :style="{ width: '320px' }"
            placeholder="Please enter something"
            search-button
            @search="search"
            :loading='searchLoading'
          >
            <template #button-icon>
              <icon-search />
            </template>
            <template #button-default> Search</template>
          </a-input-search>
          <template #content>
            <div
              style="
                border: black solid 3px;
                background-color: white;
                width: 320px;
                padding: 0 48px 48px 48px;
                overflow: hidden;
              "
            >
              <div
                style="
                  font-size: 16px;
                  height: 24px;
                  font-weight: 600;
                  text-align: left;
                "
              >
                猜你想搜
              </div>
              <div style="display: flex; flex-wrap: wrap">
                <div
                  v-for="item in KeyWord"
                  style="
                    width: 48%;
                    height: 20px;
                    text-align: left;
                  "
                >
                  <div>
                    {{ item }}
                  </div>
                </div>
              </div>
              <!--              <a-empty />-->
            </div>
          </template>
        </a-trigger>
      </div>
    </div>
    <a-space wrap>
      <!--      <a-popover title='Title'>-->
      <!--        <a-button>通知</a-button>-->
      <!--        <template #content>-->
      <!--          <p>Here is the text content</p>-->
      <!--          <p>Here is the text content</p>-->
      <!--        </template>-->
      <!--      </a-popover>-->
      <!--      <a-popover title='Title'>-->
      <!--        <a-button>私信</a-button>-->
      <!--        <template #content>-->
      <!--          <p>Here is the text content</p>-->
      <!--          <p>Here is the text content</p>-->
      <!--        </template>-->
      <!--      </a-popover>-->
      <!--      <a-popover title='Title'>-->
      <!--        <a-button>历史</a-button>-->
      <!--        <template #content>-->
      <!--          <p>Here is the text content</p>-->
      <!--          <p>Here is the text content</p>-->
      <!--        </template>-->
      <!--      </a-popover>-->
      <!--      <a-popover title='Title'>-->
      <!--        <a-button>投稿</a-button>-->
      <!--        <template #content>-->
      <!--          <p>Here is the text content</p>-->
      <!--          <p>Here is the text content</p>-->
      <!--        </template>-->
      <!--      </a-popover>-->
      <a-divider direction="vertical" />
      <a-popover position="br" title="Title">
        <a-button v-if="!user.isLogin" @click="handleClick"
          >登录
        </a-button>
        <a-button v-else @click="handleClickUpload"
          >上传
        </a-button>
      </a-popover>
    </a-space>
  </div>
  <login-modal ref="loginModal" />
  <upload-modal ref="uploadModal" />
</template>

<style scoped></style>
@/stores/user

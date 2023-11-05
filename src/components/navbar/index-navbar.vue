<script lang='ts' setup>
import { IconSearch } from '@arco-design/web-vue/es/icon';
import LoginModal from '@/components/navbar/components/login-modal.vue';
import { ref } from 'vue';

const loginModal = ref();
const handleClick = () => {
  loginModal.value.handleClick();
};

</script>

<template>
  <div style='display: flex'>
    <div style='flex: 1'>
      <div
        style='
          height: 64px;
          text-align: center;
          width: 30%;
          margin: auto;
          line-height: 64px;
        '
      >
        <a-trigger
          :click-to-close='false'
          :render-to-body='false'
          :trigger="['click']"
        >
          <a-input-search
            :style="{ width: '320px' }"
            placeholder='Please enter something'
            search-button
          >
            <template #button-icon>
              <icon-search />
            </template>
            <template #button-default> Search</template>
          </a-input-search>
          <template #content>
            <div
              style='
                border: black solid 3px;
                background-color: white;
                width: 320px;
              '
            >
              <a-empty />
            </div>
          </template>
        </a-trigger>
      </div>
    </div>
    <a-space wrap>
      <a-popover title='Title'>
        <a-button>通知</a-button>
        <template #content>
          <p>Here is the text content</p>
          <p>Here is the text content</p>
        </template>
      </a-popover>
      <a-popover title='Title'>
        <a-button>私信</a-button>
        <template #content>
          <p>Here is the text content</p>
          <p>Here is the text content</p>
        </template>
      </a-popover>
      <a-popover title='Title'>
        <a-button>历史</a-button>
        <template #content>
          <p>Here is the text content</p>
          <p>Here is the text content</p>
        </template>
      </a-popover>
      <a-popover title='Title'>
        <a-button>投稿</a-button>
        <template #content>
          <p>Here is the text content</p>
          <p>Here is the text content</p>
        </template>
      </a-popover>
      <a-divider direction='vertical' />
      <a-popover title='Title' position="br">
        <a-button @click='handleClick'>登录</a-button>
        <template #content>
          <p>Here is the text content</p>
          <p>Here is the text content</p>
        </template>
      </a-popover>
      <div></div>
    </a-space>
  </div>
  <login-modal ref='loginModal' />
</template>

<style scoped></style>

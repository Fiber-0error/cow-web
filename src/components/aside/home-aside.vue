<script lang="ts" setup>
import { IconCalendar, IconHome } from '@arco-design/web-vue/es/icon';
import { ref } from 'vue';
import router from '@/router';

const visible = ref(false);

const handleClick = () => {
  visible.value = true;
};
const handleOk = () => {
  visible.value = false;
};
const handleCancel = () => {
  visible.value = false;
};
const onClickMenuItem = (key: any) => {
  console.log(key);
};
const menuClick = (key: any) => {
  router.replace(key.key);
  console.log(key);
};
const projects = [
  {
    key: 'first_project_key',
    name: 'jojo'
  },
  {
    key: 'second_project_key',
    name: 'jojoTeam1'
  },
  {
    key: 'third_project_key',
    name: 'jojoTeam!!!'
  }
];
</script>

<template>
  <div class="logo" />
  <a-menu
    :default-open-keys="['0']"
    :default-selected-keys="['0']"
    :style="{ width: '100%' }"
    @menu-item-click="onClickMenuItem"
  >
    <a-menu-item key="0">
      <IconHome></IconHome>
      我的项目
    </a-menu-item>
    <!--    <a-sub-menu key="0">-->
    <!--      <template #title>-->
    <!--        <IconCalendar></IconCalendar>-->
    <!--        我的团队-->
    <!--      </template>-->
    <!--      <a-menu-item v-for="project in projects" :key="project.key" @click="menuClick(project)"-->
    <!--        >{{ project.name }}-->
    <!--      </a-menu-item>-->
    <!--      <a-menu-item key="new" @click="handleClick">新建团队</a-menu-item>-->
    <!--    </a-sub-menu>-->
    <a-menu-item key="1">
      <IconHome></IconHome>
      公开项目
    </a-menu-item>
    <a-menu-item key="2">
      <IconCalendar></IconCalendar>
      我的收藏
    </a-menu-item>
    <a-menu-item key="3">
      <IconCalendar></IconCalendar>
      最近访问
    </a-menu-item>
  </a-menu>
  <a-modal v-model:visible="visible" :width="360" @cancel="handleCancel" @ok="handleOk">
    <template #title> 新建团队</template>
    <div>
      <a-input :max-length="10" allow-clear placeholder="Please enter something" show-word-limit />
    </div>
  </a-modal>
</template>

<style scoped></style>

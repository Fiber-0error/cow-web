<script lang="ts" setup>
import HomeMemberTable from '@/pages/home/components/home-member-table.vue';

const memberNum = 2;
const visitorNum = 0;
const otherNum = 2;
</script>

<template>
  <a-space :size="16" direction="vertical" style="display: block">
    <a-card :bordered="false" :style="{ marginBottom: '20px' }">
      <a-row class="grid-demo" justify="center" style="height: 100px; background-color: #f7f8f9">
        <a-col :span="7">
          <h1>{{ memberNum }}</h1>
          <h1>成员</h1>
        </a-col>
        <a-col :span="1">
          <a-divider direction="vertical" style="height: 100px" />
        </a-col>
        <a-col :span="7">
          <h1>{{ visitorNum }}</h1>
          <h1>游客</h1>
        </a-col>
        <a-divider direction="vertical" style="height: 100px" />
        <a-col :span="7">
          <h1>{{ otherNum }}</h1>
          <h1>待定</h1>
        </a-col>
      </a-row>
    </a-card>
    <a-card :bordered="false" title="Arco Card">
      <template #title>
        <a-input-search :style="{ width: '160px' }" placeholder="搜索" />
      </template>
      <template #extra>
        <a-space>
          <a-button type="primary">邀请成员</a-button>
        </a-space>
      </template>
      <home-member-table />
    </a-card>
  </a-space>
</template>

<style scoped></style>

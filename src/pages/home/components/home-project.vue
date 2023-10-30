<script lang="ts" setup>
import { ref } from 'vue';
import { addProject, delProject, getProjectList } from '@/http/api/project';
import HomeProjectList from '@/pages/home/components/home-project-list.vue';

const teamList: { value: any[] } = ref([]);
const teamId = 1;
const visible = ref(false);
const newName = ref('jojo');
const fetchTeamList = async () =>
  teamList.value.splice(0, teamList.value.length, ...(await getProjectList(teamId)).data);
fetchTeamList();
console.log(teamList);
const handleClick = () => {
  visible.value = true;
};
const handleOk = async () => {
  await addProject({
    name: newName.value,
    describtion: 'jojojojojojojojo1',
    tid: 1,
    avatar:
      'https://p1-arco.byteimg.com/tos-cn-i-uwbnlip3yd/3ee5f13fb09879ecb5185e440cef6eb9.png~tplv-uwbnlip3yd-webp.webp'
  });
  await fetchTeamList();
  visible.value = false;
  newName.value = '';
};
const dropProject = async ({ id }: { id: number }) => {
  await delProject(id);
  await fetchTeamList();
  console.log(teamList);
};
</script>

<template>
  <a-card :bordered="false" title="Arco Card">
    <template #title>
      <a-input-search :style="{ width: '160px' }" placeholder="搜索" />
    </template>
    <template #extra>
      <a-space>
        <a-button type="primary" @click="handleClick">新建项目</a-button>
      </a-space>
    </template>
    <home-project-list :teamList="teamList" @refresh="dropProject" />
  </a-card>
  <a-modal v-model:visible="visible" @ok="handleOk">
    <template #title> Title</template>
    <a-input v-model:model-value="newName" :size="'large'" allow-clear placeholder="项目名称" />
  </a-modal>
</template>

<style scoped></style>

<script lang="ts" setup>
import router from '@/router';

const props = defineProps({
  teamList: Object
});
const emit = defineEmits(['refresh']);

const toInterface = ({ key }: { key: string }) => {
  router.push({ path: `/interface/${key}/http/overview` });
};

const columns = [
  {
    title: 'icon',
    width: 100,
    slotName: 'icon'
  },
  {
    title: 'name',
    slotName: 'name',
    dataIndex: 'name'
  },
  {
    title: 'Optional',
    width: 100,
    slotName: 'optional',
    align: 'right'
  }
];
const dropProject = ({ id }: { id: number }) => {
  emit('refresh', { id });
};
</script>

<template>
  <a-table
    :columns="columns"
    :data="props.teamList"
    :draggable="{ type: 'handle', width: 40 }"
    :show-header="false"
  >
    <template #icon="{ record }">
      <a-avatar :size="64" shape="square" @click="toInterface(record)">
        <img
          :alt="record"
          src="https://p1-arco.byteimg.com/tos-cn-i-uwbnlip3yd/3ee5f13fb09879ecb5185e440cef6eb9.png~tplv-uwbnlip3yd-webp.webp"
        />
      </a-avatar>
    </template>
    <template #name="{ record }">
      <a-list :bordered="false" @click="toInterface(record)">
        <a-list-item>
          <a-list-item-meta :title="record.name"></a-list-item-meta>
        </a-list-item>
      </a-list>
    </template>
    <template #optional="{ record }">
      <a-dropdown trigger="hover">
        <a-button>···</a-button>
        <template #content>
          <a-doption>修改名称</a-doption>
          <a-doption @click="dropProject(record)">删除项目</a-doption>
        </template>
      </a-dropdown>
    </template>
  </a-table>
</template>

<style scoped>
.icon-hover {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 24px;
  height: 24px;
  border-radius: 50%;
  transition: all 0.1s;
}

.icon-hover:hover {
  background-color: rgb(var(--gray-2));
}

:deep(.arco-table-tr) {
  cursor: pointer;
}
</style>

<script lang="ts" setup>
import { reactive } from 'vue';

const data = reactive([
  {
    key: '1',
    name: 'Jane Doe',
    edit: 23000,
    power: '32 Park Road, London',
    email: 'jane.doe@example.com'
  },
  {
    key: '2',
    name: 'Alisa Ross',
    edit: 25000,
    power: '35 Park Road, London',
    email: 'alisa.ross@example.com'
  },
  {
    key: '3',
    name: 'Kevin Sandra',
    edit: 22000,
    power: '31 Park Road, London',
    email: 'kevin.sandra@example.com'
  }
]);
const Jojo = () => {
  console.log(1);
};
</script>

<template>
  <a-table :cell-click="Jojo" :data="data" style="margin-top: 30px">
    <template #columns>
      <a-table-column data-index="name" title="姓名">
        <template #cell="{ record }">
          <a-avatar>
            <img
              alt="avatar"
              src="https://p1-arco.byteimg.com/tos-cn-i-uwbnlip3yd/3ee5f13fb09879ecb5185e440cef6eb9.png~tplv-uwbnlip3yd-webp.webp"
            />
          </a-avatar>
          {{ record.name }}
        </template>
      </a-table-column>
      <a-table-column data-index="email" title="邮箱"></a-table-column>
      <a-table-column data-index="power" title="团队权限">
        <template #cell="{ record }">
          <a-tag :color="'gold'">{{ record.power }}</a-tag>
        </template>
      </a-table-column>
      <a-table-column data-index="edit" title="最近修改" width="100"></a-table-column>
      <a-table-column>
        <template #cell="{ record }">
          <a-button @click="$modal.info({ title: 'Name', content: record.name })"
            >{{ record.edit }}
          </a-button>
        </template>
      </a-table-column>
    </template>
  </a-table>
</template>

<style scoped></style>

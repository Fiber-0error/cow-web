<script lang="ts" setup>
import { reactive, ref } from 'vue';

const type = ref('GET');
const url = ref('');
const name = ref('');

const data = reactive([
  {
    name: '',
    type: 'String',
    require: 'false',
    example: ''
  }
]);
const addData = () => {
  data.push({
    name: '',
    type: 'String',
    require: 'false',
    example: ''
  });
};
</script>

<template>
  <a-card>
    <a-space direction="vertical" fill>
      <a-input-group style="width: 100%">
        <a-select
          v-model:model-value="type"
          :options="['GET', 'POST', 'PUT', 'DELETE']"
          :style="{ width: '160px' }"
          default-value="GET"
        />
        <a-input v-model:model-value="url" placeholder="接口路径" />&emsp;
        <a-button>保存</a-button>
      </a-input-group>
      <a-input v-model:model-value="name" placeholder="接口名称"></a-input>
      <a-tabs default-active-key="1">
        <a-tab-pane key="1" title="Params">
          <a-table :data="data" style="margin-top: 20px">
            <template #columns>
              <a-table-column :width="160" title="参数名">
                <template #cell="{ record }">
                  <a-input v-model="record.name" />
                </template>
              </a-table-column>
              <a-table-column :width="84" title="类型">
                <template #cell="{ record }">
                  <a-select
                    v-model:model-value="record.type"
                    :options="['Number', 'String']"
                    style="width: 100px"
                  />
                </template>
              </a-table-column>
              <a-table-column :width="84" title="必需">
                <template #cell="{ record }">
                  <a-select v-model:model-value="record.require" :options="['false', 'true']" />
                </template>
              </a-table-column>
              <a-table-column title="child">
                <template #cell="{ record }">
                  <a-input v-model="record.example" />
                </template>
              </a-table-column>
              <a-table-column :width="60" title="add">
                <template #title="">
                  <a-button @click="addData">新建</a-button>
                </template>
                <template #cell="">
                  <a-button>新增</a-button>
                </template>
              </a-table-column>
            </template>
          </a-table>
          <a-table :data="data" style="margin-top: 20px">
            <template #columns>
              <a-table-column :width="160" title="参数名">
                <template #cell="{ record }">
                  <a-input v-model="record.name" />
                </template>
              </a-table-column>
              <a-table-column :width="84" title="类型">
                <template #cell="{ record }">
                  <a-select
                    v-model:model-value="record.type"
                    :options="['Number', 'String']"
                    style="width: 100px"
                  />
                </template>
              </a-table-column>
              <a-table-column :width="84" title="必需">
                <template #cell="{ record }">
                  <a-select v-model:model-value="record.require" :options="['false', 'true']" />
                </template>
              </a-table-column>
              <a-table-column title="child">
                <template #cell="{ record }">
                  <a-input v-model="record.example" />
                </template>
              </a-table-column>
              <a-table-column :width="60" title="add">
                <template #title="">
                  <a-button @click="addData">新建</a-button>
                </template>
                <template #cell="">
                  <a-button>新增</a-button>
                </template>
              </a-table-column>
            </template>
          </a-table>
        </a-tab-pane>
      </a-tabs>
    </a-space>
  </a-card>
</template>

<style scoped></style>

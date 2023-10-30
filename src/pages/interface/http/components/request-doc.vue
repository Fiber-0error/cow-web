<script lang="ts" setup>
// const handleSelect = (v: string) => {
//   console.log(v);
// };

const docInfo = {
  title: '得到所有类型',
  type: 'GET',
  url: 'http://localhost:8080/type/getType'
  // state: '开发中'
};
const docDetail = {
  createTime: '2023年6月3日',
  createTor: 'dojo',
  changeTime: '几秒前',
  changeTor: 'dio',
  kikiTor: 'none',
  directory: 'type'
};
// const stateMap = [];
const requestParameters = [
  { name: 'typeName', type: 'string', state: '可选' },
  { name: 'jojoTypeName', type: 'number', state: '可选' }
];
const responseParameters = [
  {
    name: 'jojoName',
    type: 'string',
    state: '可选',
    children: [
      { name: 'jojoChildren', type: 'number', state: '可选' },
      { name: 'jojoChildren', type: 'number', state: '可选' },
      { name: 'jojoChildren', type: 'number', state: '可选' }
    ]
  }
];
</script>

<template>
  <a-row justify="space-between">
    <a-col flex="auto">{{ docInfo.title }}</a-col>
    <a-col flex="200px">
      <a-button type="primary">运行</a-button>
      <a-button>删除</a-button>
    </a-col>
  </a-row>
  <a-row style="margin: 12px 0">
    <a-col>
      <a-tag color="green">{{ docInfo.type }}</a-tag>
      <span>{{ docInfo.url }}</span>
      <!--      <a-dropdown @select="handleSelect">-->
      <!--        <a-button>-->
      <!--          //接口开发状态-->
      <!--          {{ docInfo.state }}-->
      <!--          <icon-down />-->
      <!--        </a-button>-->
      <!--        <template #content>-->
      <!--          <a-doption>Option 1</a-doption>-->
      <!--          <a-doption>Option 2</a-doption>-->
      <!--          <a-doption>Option 3</a-doption>-->
      <!--        </template>-->
      <!--      </a-dropdown>-->
    </a-col>
  </a-row>
  <a-row style="margin: 12px 0">
    <a-col flex="180px">创建时间 {{ docDetail.createTime }}</a-col>
    <a-col flex="140px">修改时间 {{ docDetail.changeTime }}</a-col>
    <a-col flex="100px">修改者 {{ docDetail.changeTor }}</a-col>
    <a-col flex="100px">创建者 {{ docDetail.createTor }}</a-col>
    <a-col flex="120px">责任人 {{ docDetail.kikiTor }}</a-col>
    <a-col flex="80px">目录 {{ docDetail.directory }}</a-col>
  </a-row>
  <a-row>
    <a-col :span="24">
      <a-card title="请求参数">
        <a-table
          :data="requestParameters"
          :default-expand-all-rows="true"
          :show-header="false"
          scroll="scroll"
          style="margin-top: 20px"
        >
          <template #columns>
            <a-table-column data-index="first">
              <template #cell="{ record }">
                <a-tag color="blue">{{ record.name }}</a-tag>
                <span>{{ record.type }}</span>
              </template>
            </a-table-column>
            <a-table-column data-index="salary"></a-table-column>
            <a-table-column data-index="address"></a-table-column>
            <a-table-column data-index="email"></a-table-column>
            <a-table-column title="Optional">
              <template #cell="{ record }">
                <a-button @click="$modal.info({ title: 'Name', content: record.name })"
                  >view
                </a-button>
              </template>
            </a-table-column>
          </template>
        </a-table>
      </a-card>
    </a-col>
  </a-row>
  <a-row>
    <a-col :span="16" style="height: auto">
      <a-card style="height: 600px" title="返回响应">
        <a-table
          :data="responseParameters"
          :default-expand-all-rows="true"
          :show-header="false"
          scroll="scroll"
          style="margin-top: 20px"
        >
          <template #columns>
            <a-table-column data-index="first">
              <template #cell="{ record }">
                <a-tag color="blue">{{ record.name }}</a-tag>
                <span>{{ record.type }}</span>
              </template>
            </a-table-column>
            <a-table-column data-index="salary"></a-table-column>
            <a-table-column data-index="address"></a-table-column>
            <a-table-column data-index="email"></a-table-column>
            <a-table-column title="Optional">
              <template #cell="{ record }">
                <a-button @click="$modal.info({ title: 'Name', content: record.name })"
                  >view
                </a-button>
              </template>
            </a-table-column>
          </template>
        </a-table>
      </a-card>
    </a-col>
    <a-col :span="8">
      <a-card style="height: 600px" title="示例"></a-card>
    </a-col>
  </a-row>
</template>

<style scoped></style>

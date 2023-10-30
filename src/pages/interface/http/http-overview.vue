<script lang="ts" setup></script>

<template>
  <a-card :bordered="false">
    <a-space direction="vertical" fill>
      <a-card title="项目统计">
        <a-row gutter="24">
          <a-col :span="4">
            <div class="textbox">
              <h2>18</h2>
              <p>接口数</p>
            </div>
          </a-col>
          <a-col :span="4">
            <div class="textbox">
              <h2>3</h2>
              <p>接口用例数</p>
            </div>
          </a-col>
          <a-col :span="4">
            <div class="textbox">
              <h2>1</h2>
              <p>文档数</p>
            </div>
          </a-col>
          <a-col :span="4">
            <div class="textbox">
              <h2>4</h2>
              <p>数据模型数</p>
            </div>
          </a-col>
          <a-col :span="4">
            <div class="textbox">
              <h2>2</h2>
              <p>测试场景数</p>
            </div>
          </a-col>
        </a-row>
      </a-card>
      <!--      <a-card title="分享列表">-->
      <!--        <a-row>-->
      <!--          <a-col>-->
      <!--            <p>将接口文档以URL形式分享出去，方便外部团队在线查看</p>-->
      <!--          </a-col>-->
      <!--          <a-col style="text-align: center">-->
      <!--            <a-button type="text">新建分享</a-button>-->
      <!--          </a-col>-->
      <!--        </a-row>-->
      <!--      </a-card>-->
      <a-card title="自动导入">
        <a-row>
          <a-col>
            <p>自动将OpenAPI (Swagger)或apiDoc格式的在线URL接口数据定时导入到Apifox。</p>
          </a-col>
          <a-col>
            <a-button type="primary">去开启</a-button>
          </a-col>
        </a-row>
      </a-card>
      <!--      <a-card title="代码生成">-->
      <!--        <a-row>-->
      <!--          <a-col>-->
      <!--            <p>-->
      <!--              根据接口/模型定义，自动生成各种语言/框架（如TypeScript、Java、Go、Swift、ObjectiveC、Kotlin.Dart、C++、C#、Rust-->
      <!--              等130种语言及框架)的业务代码(如Model、Controller、单元测试代码等)-->
      <!--            </p>-->
      <!--          </a-col>-->
      <!--          <a-col>-->
      <!--            <a-button type="primary">立即生成</a-button>-->
      <!--          </a-col>-->
      <!--        </a-row>-->
      <!--      </a-card>-->
    </a-space>
  </a-card>
</template>

<style lang="less" scoped>
:deep(.arco-card-header) {
  border: none;
}

.textbox {
  h2 {
    margin: 0;
  }
}

p {
  margin-top: 0;
}
</style>

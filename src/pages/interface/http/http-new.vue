<script lang="ts" setup></script>

<template>
  <a-row gutter="16" style="width: 800px; margin: 0 auto">
    <a-col :span="6">
      <router-link to="#">
        <a-card>
          <template #cover>
            <div>
              <img
                :style="{ width: '100%' }"
                alt="dessert"
                src="https://p1-arco.byteimg.com/tos-cn-i-uwbnlip3yd/a20012a2d4d5b9db43dfc6a01fe508c0.png~tplv-uwbnlip3yd-webp.webp"
              />
            </div>
          </template>
          新建接口
        </a-card>
      </router-link>
    </a-col>
    <a-col :span="6">
      <router-link to="#">
        <a-card>
          <template #cover>
            <div>
              <img
                :style="{ width: '100%' }"
                alt="dessert"
                src="https://p1-arco.byteimg.com/tos-cn-i-uwbnlip3yd/a20012a2d4d5b9db43dfc6a01fe508c0.png~tplv-uwbnlip3yd-webp.webp"
              />
            </div>
          </template>
          快捷请求
        </a-card>
      </router-link>
    </a-col>
    <a-col :span="6">
      <router-link to="#">
        <a-card>
          <template #cover>
            <div>
              <img
                :style="{ width: '100%' }"
                alt="dessert"
                src="https://p1-arco.byteimg.com/tos-cn-i-uwbnlip3yd/a20012a2d4d5b9db43dfc6a01fe508c0.png~tplv-uwbnlip3yd-webp.webp"
              />
            </div>
          </template>
          新建Markdown
        </a-card>
      </router-link>
    </a-col>
    <a-col :span="6">
      <router-link to="#">
        <a-card>
          <template #cover>
            <div>
              <img
                :style="{ width: '100%' }"
                alt="dessert"
                src="https://p1-arco.byteimg.com/tos-cn-i-uwbnlip3yd/a20012a2d4d5b9db43dfc6a01fe508c0.png~tplv-uwbnlip3yd-webp.webp"
              />
            </div>
          </template>
          新建数据模型
        </a-card>
      </router-link>
    </a-col>
  </a-row>
</template>

<style scoped></style>

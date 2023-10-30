<script lang="ts" setup>
import RequestDoc from '@/pages/interface/http/components/request-doc.vue';
</script>

<template>
  <a-card :bordered="false">
    <a-tabs default-active-key="1">
      <a-tab-pane key="1" title="文档">
        <request-doc />
      </a-tab-pane>
      <a-tab-pane key="2" title="修改文档"></a-tab-pane>
      <a-tab-pane key="3" title="运行"></a-tab-pane>
      <a-tab-pane key="4" title="Mock"></a-tab-pane>
    </a-tabs>
  </a-card>
</template>

<style scoped></style>

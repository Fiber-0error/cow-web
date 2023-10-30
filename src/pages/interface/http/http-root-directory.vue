<script lang="ts" setup>
import DirectoryRequest from '@/pages/interface/http/components/directory-request.vue';
import DirectoryDoc from '@/pages/interface/http/components/directory-doc.vue';
</script>

<template>
  <a-card :bordered="false">
    <a-tabs default-active-key="2">
      <a-tab-pane key="1" title="全部接口">
        <directory-request />
      </a-tab-pane>
      <a-tab-pane key="2" title="文档">
        <directory-doc />
      </a-tab-pane>
    </a-tabs>
  </a-card>
</template>

<style scoped></style>

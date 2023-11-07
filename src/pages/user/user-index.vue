<script lang="ts" setup>
import { userCollectList } from '@/http/api/login';
import { ref } from 'vue';

const userInfo = ref({});
userCollectList().then((r) => {
  console.log(r);
  userInfo.value = r;
});
</script>

<template>
  <div style='background-color: #2c3e50;'>
    <div style="width: 60%;min-height: 100vh;margin: auto">
      <user-info> </user-info>
    </div>
  </div>
</template>

<style scoped></style>

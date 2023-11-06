<script lang="ts" setup>
import { userCollectList } from '@/http/api/login';

userCollectList().then((r) => {
  console.log(r);
});
</script>

<template></template>

<style scoped></style>

<script lang='ts' setup>
import { computed } from 'vue';

const randomHeight = computed(() => Math.floor(Math.random() * 400 + 150));
</script>

<template>
  <div :style='{height: randomHeight+"px"}' style='border: #2c3e50 2px dashed'>

  </div>
</template>

<style scoped>

</style>

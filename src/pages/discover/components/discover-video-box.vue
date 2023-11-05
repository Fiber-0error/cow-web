<script lang='ts' setup>
</script>

<template>
  <div class='card' style='width: 300px;border: 1px dodgerblue solid;border-radius: 8px;margin: 8px'>
    <div>
      <slot></slot>
    </div>
    <div style='height: 50px;background-color: rgba(128,128,128,0.3)'>
      <slot name='content'>

      </slot>
    </div>
  </div>
</template>

<style scoped>

</style>

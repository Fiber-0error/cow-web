<template>
  <div class="info-container">
    <div class="user-name">
      @{{ uploadUser }}
    </div>
    <div class="describe">
      <span class="type">
        {{ type }}
      </span>
      <span v-for="(item, index) in tagArr" :key="index">#{{item}}</span>
      <p>
        {{ describe }}
      </p>

    </div>
  </div>
</template>
<script setup lang='ts'>
const props = defineProps([ 'videoItem'])
const {uploadUser, type, name, describe, tag} = props.videoItem;
const tagArr = tag && tag.split(',');
</script>
<style lang='scss' scoped>
.info-container{
  position: absolute;
  bottom: 70px;
  left: 10px;
  color: #fff;
  user-select: none;
  width: 30%;

  .user-name{
    font-size: 1.5rem;
  }

  .describe{
    font-size: 1rem;
    .type{
      color: #165dFF;
    }
  }
}
</style>

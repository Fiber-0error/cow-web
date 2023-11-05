<template>
  <div class="info-container">
    <div class="user-name">
      @{{ uploadUser }}
    </div>
    <div class="describe">
      <span class="type">
        #{{ type }}
      </span>
      {{ tag }}
      {{ describe }}

    </div>
  </div>
</template>
<script setup lang='ts'>
const props = defineProps([ 'videoItem'])
const {uploadUser, type, name, describe, tag} = props.videoItem;

</script>
<style lang='scss' scoped>
.info-container{
  position: absolute;
  bottom: 150px;
  left: 10px;
  color: #fff;

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

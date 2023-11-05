<template>
  <div class="right-container">
    <div class="img-container">
      <img src="http://e.hiphotos.baidu.com/image/pic/item/a1ec08fa513d2697e542494057fbb2fb4316d81e.jpg" alt="">
    </div>
    <div class="like-contianer">
      <icon-heart-fill class="right-icon" @click="handleClickStar" />
      <span>{{likeCount ? likeCount : '123'}}</span>
    </div>
    <icon-star-fill class="right-icon" />
  </div>
</template>
<script setup lang='ts'>
import { IconHeartFill , IconStarFill} from '@arco-design/web-vue/es/icon';
const props = defineProps([ 'videoItem'])
const {likeCount,isLike} = props.videoItem;
console.log('videoInfo', props.videoItem)

/**
 * 处理点击喜欢
 */
const handleClickStar = () => {
  
}
</script>
<style lang='scss' scoped>

.right-container{
  position: absolute;
  background-color: transparent;
  color: #fff;
  top: 50%;
  right: 20px;
  z-index: 999;
  display: flex;
  flex-direction: column;
  align-items: center;
}

img {
    width: 40px;
    height: 40px;
    border-radius: 50%;
    }

    .like-contianer{
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: center;
      span{
        font-size: 12px;
      }
    }
    .right-icon{
      margin-top: 15px;
      width: 32px;
      height: 32px;
      cursor: pointer;
    }
</style>

<template>
  <div class="right-container">
    <div class="img-container">
      <img src="http://e.hiphotos.baidu.com/image/pic/item/a1ec08fa513d2697e542494057fbb2fb4316d81e.jpg" alt="">
    </div>
    <div class="like-contianer">
      <icon-heart-fill class="right-icon" :style="{color: liked ? 'red' : ''}" @click="handleClickStar" />
      <span>{{likeCount}}</span>
    </div>
    <div class="like-contianer">
      <icon-star-fill class="right-icon" :style="{color: collect ? 'red' : ''}" @click="handleClickCollect"  />
    </div>

  </div>
</template>
<script setup lang='ts'>
import { collectVideo, likeVideo } from '@/http/api/video';
import { useUserStore } from '@/stores/user';
import { IconHeartFill , IconStarFill} from '@arco-design/web-vue/es/icon';
import { message } from 'ant-design-vue';
import { ref } from 'vue';
const props = defineProps([ 'videoItem', 'changeData']);
const emits = defineEmits(['changeData'])
const {isLike,id,isCollect} = props.videoItem;

const liked = ref(isLike);
const likeCount = ref(props.videoItem.likeCount ? props.videoItem.likeCount : 0);
const collect = ref(isCollect);

const user = useUserStore();

/**
 * 处理点击喜欢
 */
const handleClickStar = () => {
  if (!user.isLogin) {
     message.error('请先登录！');
    return;
  }
    likeVideo(id).then(res => {
      liked.value = res.data;
      if(res.data){
        likeCount.value = likeCount.value ? likeCount.value + 1 : 1;
        message.success('喜欢成功！');
      } else {
        likeCount.value = likeCount.value ? likeCount.value - 1 : 0;
      }
    })
}

const handleClickCollect = () => {
  if (!user.isLogin) {
      message.error('请先登录！');

    return;
  }
  collectVideo(id).then(res => {
      collect.value = res.data;
      if(res.data) {
        message.success('收藏成功！');
      }
    })
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

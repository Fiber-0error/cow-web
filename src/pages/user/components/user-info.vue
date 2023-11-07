<script lang="ts" setup>
import { onMounted, ref } from 'vue';
import { useUserStore } from '@/stores/user';
import { userGetBaseInfo } from '@/http/api/login';

const userInfo = ref({});
const niuMa = ref();
const user = useUserStore();
console.log(123);
onMounted(() => {
  const id = localStorage.getItem('id');
  niuMa.value = localStorage.getItem('token').substring(10,18);
  userGetBaseInfo(id).then((r) => {
    console.log(r);
    userInfo.value = r.data;
  });
});
</script>

<template>
  <div style="display: flex; padding: 48px">
    <div>
      <a-avatar :size="120">
        <img
          alt="avatar"
          src="https://p1-arco.byteimg.com/tos-cn-i-uwbnlip3yd/3ee5f13fb09879ecb5185e440cef6eb9.png~tplv-uwbnlip3yd-webp.webp"
        />
      </a-avatar>
    </div>
    <div style="color: white; padding: 24px">
      <div style="font-size: 24px">
        {{ userInfo.name }}
      </div>
      <div style="font-size: 16px; color: grey">
        关注 &nbsp; <span style="color: white">0</span>
      </div>
      <div style="color: grey">牛马号: {{niuMa}}</div>
    </div>
  </div>
</template>

<style scoped></style>

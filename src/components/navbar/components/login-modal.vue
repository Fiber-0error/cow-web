<script lang='ts' setup>
import { reactive, ref } from 'vue';
import { userLogin, userRegister } from '@/http/api/login';
import { useUserStore } from '@/stores/user';

const user = useUserStore();
const visible = ref(false);


const activeKey = ref('1');
const form = reactive({
  username: 'test',
  password: '123456'
});
const handleClick = () => {
  visible.value = true;
};
defineExpose({
  handleClick
});
const handleBeforeOk = (done) => {
  console.log(activeKey);
  if (activeKey.value === '1') {
    userLogin(form).then(({ data }) => {
      localStorage.setItem('token', data.token);
      user.setToken(data.token);
    });
  } else if (activeKey.value === '2') {
    userRegister(form).then(() => {
      console.log('注册成功');
      userLogin(form).then(({ data }) => {
        localStorage.setItem('token', data.token);
      }).catch(() => {
        console.log('注册失败');
      });
    });
  } else {

  }
  window.setTimeout(() => {
    done();
  }, 1000);
};
const handleCancel = () => {
  visible.value = false;
};
</script>

<template>
  <a-modal v-model:visible='visible' title='Login Modal' @cancel='handleCancel' @before-ok='handleBeforeOk'>
    <a-tabs v-model:active-key='activeKey'>
      <a-tab-pane key='1' title='登录'>
        <a-form :model='form'>
          <a-form-item field='name' label='Name'>
            <a-input v-model='form.username' />
          </a-form-item>
          <a-form-item field='post' label='Post'>
            <a-input v-model='form.password' />
          </a-form-item>
        </a-form>
      </a-tab-pane>
      <a-tab-pane key='2' title='注册'>
        <a-form :model='form'>
          <a-form-item field='name' label='Name'>
            <a-input v-model='form.username' />
          </a-form-item>
          <a-form-item field='post' label='Post'>
            <a-input v-model='form.password' />
          </a-form-item>
        </a-form>
      </a-tab-pane>
    </a-tabs>
  </a-modal>
</template>

<style scoped>

</style>

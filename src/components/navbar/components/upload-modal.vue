<script lang="ts" setup>
import { reactive, ref } from 'vue';
import type {
  UploadChangeParam,
  UploadProps
} from 'ant-design-vue';
import { message } from 'ant-design-vue';
const uploadable = ref(true);
const visible = ref(false);
const formRef = ref();
const handleClick = () => {
  visible.value = true;
  fileList.value = [];
};

defineExpose({
  handleClick
});
const handleBeforeOk = (done) => {
  window.setTimeout(() => {
    done();
  }, 1000);
};
const handleCancel = () => {
  visible.value = false;
};

const formState = reactive({
  name: '',
  describe: '',
  type: '',
  url: ''
});
const onFinish = (values: any) => {
  console.log('Success:', values);
};

const onFinishFailed = (errorInfo: any) => {
  console.log('Failed:', errorInfo);
};

const fileList = ref([]);
const handleChange = (info: UploadChangeParam) => {
};

const beforeUpload: UploadProps['beforeUpload'] = async (file) => {
  return new Promise((resolve, reject) => {
    const isLt100M = file.size / 1024/ 1024 < 100;
    if(!isLt100M) {
      message.error('file is large than 100M!');
      reject(new Error());
    }
    const fileType = file.name.substring(file.name.lastIndexOf('.') + 1)
    const type = ['MP4','MOV','WMV','FLV','AVI','AVCHD','WebM','MKV']
    const isPNG = type.includes(fileType.toUpperCase());
    if (!isPNG) {
      message.error(`${file.name} is not a video file`);
      reject(new Error());
    }
    resolve(true);
  })
};

const headers = {
  Authorization: localStorage.getItem('token'),
};

const url = 'https://cool.ldqc.xyz/cow-api/common/video/upload';
</script>

<template>
  <a-modal
    v-model:visible="visible"
    title="Upload Modal"
    @cancel="handleCancel"
    @before-ok="handleBeforeOk"
  >
    <a-form
    ref="formRef"
      :label-col="{ span: 8 }"
      :model="formState"
      :wrapper-col="{ span: 16 }"
      autocomplete="off"
      name="basic"
      @finish="onFinish"
      @finishFailed="onFinishFailed"
    >
      <a-form-item
        :rules="[
          {
            required: true,
            message: 'Please input your name!'
          }
        ]"
        label="name"
        name="name"
      >
        <a-input v-model:value="formState.name" />
      </a-form-item>

    <a-form-item
      label="describe"
      name="describe"
      :rules="[{ required: true, message: 'Please input your describe!' }]"
    >
      <a-input v-model:value="formState.describe" />
    </a-form-item>

     <a-form-item
      label="type"
      name="type"
      :rules="[{ required: true, message: 'Please input your type!' }]"
    >
      <a-input v-model:value="formState.type" />
    </a-form-item>

      <a-form-item
        :rules="[
          {
            required: true,
            message: 'Please upload your video!'
          }
        ]"
        label="File"
        name="remember"
      >
       <a-upload
    v-model:file-list="fileList"
    name="file"
    :multiple="false"
    :action="url"
    :headers="headers"
    @before-upload="beforeUpload"
    @change="handleChange"
  >
    <a-button>
      <upload-outlined></upload-outlined>
      Click to Upload
    </a-button>
  </a-upload>
      </a-form-item>
    </a-form>
  </a-modal>
</template>

<style scoped></style>

<script lang='ts' setup>
import { ref , reactive} from 'vue';
import { PlusOutlined, LoadingOutlined , InboxOutlined} from '@ant-design/icons-vue';
import { message } from 'ant-design-vue';
import type { UploadChangeParam, UploadProps } from 'ant-design-vue';

const visible = ref(false);
const handleClick = () => {
  visible.value = true;
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
  url:'',
});
const onFinish = (values: any) => {
  console.log('Success:', values);
};

const onFinishFailed = (errorInfo: any) => {
  console.log('Failed:', errorInfo);
};

const fileList = ref([]);
const handleChange = (info: UploadChangeParam) => {
  const status = info.file.status;
  if (status !== 'uploading') {
    console.log(info.file, info.fileList);
  }
  if (status === 'done') {
    message.success(`${info.file.name} file uploaded successfully.`);
  } else if (status === 'error') {
    message.error(`${info.file.name} file upload failed.`);
  }
};

const beforeUpload: UploadProps['beforeUpload'] = file => {
  const type = ['MP4','MOV','WMV','FLV','AVI','AVCHD','WebM','MKV']
  const isPNG = type.includes(`${file.type}`.toUpperCase());
  if (!isPNG) {
    message.error(`${file.name} is not a video file`);
  }
  console.log(file)
  return false;
};

const url = "http://cool.ldqc.xyz/cow-api/video/upload";
</script>

<template>
  <a-modal v-model:visible='visible' title='Upload Modal' @cancel='handleCancel' @before-ok='handleBeforeOk'>
    <a-form
    :model="formState"
    name="basic"
    :label-col="{ span: 8 }"
    :wrapper-col="{ span: 16 }"
    autocomplete="off"
    @finish="onFinish"
    @finishFailed="onFinishFailed"
  >
    <a-form-item
      label="name"
      name="name"
      :rules="[{ required: true, message: 'Please input your name!' }]"
    >
      <a-input v-model:value="formState.name" />
    </a-form-item>

    <a-form-item
      label="describe"
      name="describe"
      :rules="[{ required: true, message: 'Please input your describe!' }]"
    >
      <a-input-password v-model:value="formState.describe" />
    </a-form-item>

     <a-form-item
      label="type"
      name="type"
      :rules="[{ required: true, message: 'Please input your type!' }]"
    >
      <a-input-password v-model:value="formState.type" />
    </a-form-item>

    <a-form-item name="remember"
      label="File"
      :rules="[{ required: true, message: 'Please upload your video!' }]"
     >
        <a-upload
      v-model:file-list="fileList"
        :multiple="false"
      :action="url"
      :before-upload="beforeUpload"
    >
      <a-button>
        <upload-outlined></upload-outlined>
        Upload
      </a-button>
    </a-upload>
    </a-form-item>


  </a-form>

  </a-modal>
</template>

<style scoped>



</style>

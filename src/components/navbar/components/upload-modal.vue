<script lang="ts" setup>
import { reactive, ref } from 'vue';
import type {
  UploadChangeParam,
  UploadProps
} from 'ant-design-vue';
import { message } from 'ant-design-vue';
import { uploadVideo } from '@/http/api/video';
import { useVideoStore } from '@/stores/video';

const videoStore = useVideoStore();
videoStore.getUploadBaseUrl();
const visible = ref(false);
const formRef = ref();
const optionList = ref([
  {
    label: '知识',
    value: '知识',
  },
  {
    label: '体育',
    value: '体育',
  },
  {
    label: '二次元',
    value: '二次元',
  },
  {
    label: '游戏',
    value: '游戏',
  },
  {
    label: '音乐',
    value: '音乐',
  },
])

    const VideoType = ['MP4','MOV','WMV','FLV','AVI','AVCHD','WebM','MKV']
    const imgType = ['PNG','JPG']


const videoFileList = ref([]);

const handleClick = () => {
  visible.value = true;
  fileList.value = [];
  formState.describe = '';
  formState.type = '';
  formState.name = '';
  formState.url = '';
  formState.imgUrl = '';
};

defineExpose({
  handleClick
});
const handleBeforeOk = async (done) => {
  console.log(formState);
  if (ceheckForm()){
    console.log('data', formState);
    const {describe,imgUrl, name, url, type} = formState;
    const res = await uploadVideo({
      describe,
      imgUrl,
      name,
      url,
      type
    });
    message.success(res.msg);
    console.log('res', res);
  } else {
    message.error('请填写表单!')
    return;
  }
    done();
};

const ceheckForm = () => {
  const {name, describe, type, url} = formState;
  if(name && describe && type && url) return true
  return false
}

const handleCancel = () => {
  visible.value = false;
};

const formState = reactive({
  name: '',
  describe: '',
  type: '',
  url: '',
  imgUrl: '',
});
const onFinish = (values: any) => {
  console.log('Success:', values);
};

const onFinishFailed = (errorInfo: any) => {
  console.log('Failed:', errorInfo);
};

const fileList = ref([]);
const handleChange = async (info: UploadChangeParam) => {
  const [ainfor] = info;
  const {status,file} = ainfor;
  if (status === 'done') {
      console.log('info', info);
      const {response} = ainfor;
      const afterUrl = response.data.key;
      const fileType = file.name.substring(file.name.lastIndexOf('.') + 1).toUpperCase();
      if(response.msg !== '处理成功') {
        message.error('上传失败')
        return;
      }
      if(imgType.includes(fileType)){
        formState.imgUrl = videoStore.uploadBaseUrl + '/'+ afterUrl;
      }else {
        formState.url = videoStore.uploadBaseUrl + '/' + afterUrl;
      }
  }
};

const beforeUpload: UploadProps['beforeUpload'] = async (file) => {
  return new Promise((resolve, reject) => {
    const isLt100M = file.size / 1024/ 1024 < 100;
    if(!isLt100M) {
      message.error('file is large than 100M!');
      reject(new Error());
    }
    const fileType = file.name.substring(file.name.lastIndexOf('.') + 1)
    const isPNG = VideoType.includes(fileType.toUpperCase());
    if (!isPNG) {
      message.error(`${file.name} is not a video file`);
      reject(new Error());
    }
    resolve(true);
  })
};

const headers = {
  'Authorization': localStorage.getItem('token'),
  'X-Requested-With': null
};


const beforeUploadImg = async (file) => {
  return new Promise((resolve, reject) => {
    const isLt100M = file.size / 1024/ 1024 < 100;
    if(!isLt100M) {
      message.error('file is large than 100M!');
      reject(new Error());
    }
    const fileType = file.name.substring(file.name.lastIndexOf('.') + 1)
    const isPNG = imgType.includes(fileType.toUpperCase());
    if (!isPNG) {
      message.error(`${file.name} is not a IMG file`);
      reject(new Error());
    }
    resolve(true);
  })
};

const imgUrl = 'https://cool.ldqc.xyz/cow-api/common/img/upload'
const videoUrl = 'https://cool.ldqc.xyz/cow-api/common/video/upload';

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
        <a-input v-model="formState.name" />
      </a-form-item>

    <a-form-item
      label="describe"
      name="describe"
      :rules="[{ required: true, message: 'Please input your describe!' }]"
    >
      <a-input v-model="formState.describe" />
    </a-form-item>

     <a-form-item
      label="type"
      name="type"
      :rules="[{ required: true, message: 'Please input your type!' }]"
    >
       <a-select v-model="formState.type" placeholder="Please select a type" :options="optionList">
      </a-select>
    </a-form-item>

    <a-form-item
        :rules="[
          {
            required: true,
            message: 'Please input your img url!'
          }
        ]"
        label="Img url"
        name="remember"
      >
          <a-upload
        v-model:file-list="fileList"
        name="file"
        list-type="picture-card"
        class="avatar-uploader"
        :show-upload-list="false"
        :multiple="false"
        :action="imgUrl"
        :headers="headers"
        :before-upload="beforeUploadImg"
        @change="handleChange"
      >
        <img v-if="formState.imgUrl" :src="formState.imgUrl" alt="file" />
        <div v-else>
          <div class="ant-upload-text">Upload</div>
        </div>
      </a-upload>
      </a-form-item>

      <a-form-item
        :rules="[
          {
            required: true,
          }
        ]"
        label="File url"
        name="remember"
      >
       <a-upload
      v-model:file-list="videoFileList"
        name="file"
      :headers="headers"
        :multiple="false"
      :before-upload="beforeUpload"
      @change="handleChange"
      :max-count="1"
      :action="videoUrl"
    >
      <a-button>
        <upload-outlined></upload-outlined>
        Upload (Max: 1)
      </a-button>
    </a-upload>
      </a-form-item>
    </a-form>
  </a-modal>
</template>

<style scoped></style>

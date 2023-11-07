import { defineStore } from "pinia";
import { userWatchVideo } from '@/http/api/video';
import { getBaseUploadUrl } from "@/http/api/cow";

export const useVideoStore = defineStore('video', {
  state: () => {
    return {
      videoList: [],
      uploadBaseUrl: '',
    }
  },
  actions: {
    setVideoList(videoList: any){
      this.videoList = videoList;
    },
     pushVideoList(newVideoList: any[]) {
      //@ts-ignore
       this.videoList = [...this.videoList, ...newVideoList]
    },
    async watchVideo(videoId) {
      const { data: videoResData } = await userWatchVideo(videoId);
      const videoItemIndex = this.videoList.findIndex(item => item.id === videoId);
      const videoItem = this.videoList[videoItemIndex];
      // @ts-ignore
      this.videoList[videoItemIndex] = {...videoItem, ...videoResData};
    },
    async getUploadBaseUrl() {
      const res = await getBaseUploadUrl();
      if(res.msg === '处理成功'){
        this.uploadBaseUrl = res.data.host;
      }
    }
  },
})

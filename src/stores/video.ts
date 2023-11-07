import { defineStore } from "pinia";
import { userWatchVideo } from '@/http/api/video';

export const useVideoStore = defineStore('video', {
  state: () => {
    return {
      videoList: [],
    }
  },
  actions: {
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
    }
  },
})

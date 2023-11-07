import serviceAxios from '@/http';

const VIDEO_URL = '/video/';
const projectRequest = (url: string, method: string, params?: object) => {
    return serviceAxios({
        url: VIDEO_URL + url, method, ...params
    });
};
export const getVideoNext = () => {
  return projectRequest(`swapper/next`, 'get');
};

export const userWatchVideo = (videoId: number) => {
  if (!videoId) {
    return;
  }
  return projectRequest(`watch?videoId=${videoId}`, 'get')
}

export const updateVideoWatchProgress = (videoId: number, watchProgress: string) => {
  if (!videoId || !watchProgress) {
    return;
  }
  return projectRequest(`/flash/watchProgress?videoId=${videoId}&watchProgress=${watchProgress}`, 'get')

}

export const likeVideo = (videoId: number) => {
  if(!videoId) {
    return;
  }
  return projectRequest(`like?videoId=${videoId}`, 'get')
}

export const collectVideo = (videoId: number) => {
  if (!videoId) {
    return;
  }
  return projectRequest(`collect?videoId=${videoId}`, 'get')
}

export const recommendVideo = (type: string,size:number) => {
  return projectRequest(`recommend?type=${type}&size=${size}`, 'get')
}

export const searchVideo = (keyWord:string,current:number,type: string,size:number) => {
  return projectRequest(`search?keyWord=${keyWord}&current=${current}&type=${type}&size=${size}`, 'get')
}

export const uploadVideo = (params) => {
  return projectRequest('upload', 'post', {
    params
  });
}


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

export const updateVideoWatchProgress = () => {

}

export const likeVideo = (videoId: number) => {
  return projectRequest('like', 'get', {videoId})
}

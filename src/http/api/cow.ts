
import serviceAxios from '@/http';

const COW_URL = '/cow/';
const projectRequest = (url: string, method: string, params?: object) => {
  return serviceAxios({
    url: COW_URL + url, method, ...params
  });
};

export const getBaseUploadUrl = () => {
  return projectRequest('getInfo', 'get');
}

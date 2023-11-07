import serviceAxios from '@/http';

const LOGIN_URL = '/video/user/';
const userRequest = (url: string, method: string, params?: object) => {
  return serviceAxios({
    url: LOGIN_URL + url, method, ...params
  });
};
export const getUserList = () => {
  return userRequest(`list?current=0&size=7`, 'get' );
};


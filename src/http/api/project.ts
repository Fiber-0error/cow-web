import serviceAxios from '@/http';

const PROJECT_URL = '/project/';
const projectRequest = (url: string, method: string, params?: object) => {
  return serviceAxios({
    url: PROJECT_URL + url,
    method: method,
    ...params
  });
};
export const addUser = () => {
  return serviceAxios({
    url: '/users/add',
    method: 'post',
    data: {
      username: 'Fred',
      password: '12345'
    }
  });
};
export const addProject = (data: object) => {
  return projectRequest('add', 'post', { data });
};
export const delProject = (id: number) => {
  return projectRequest(`del/${id}`, 'delete');
};
export const getProjectList = (tid: number) => {
  return projectRequest(`findListByTid/${tid}`, 'get');
};

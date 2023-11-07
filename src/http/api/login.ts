import serviceAxios from '@/http';

const LOGIN_URL = '/account/';
const loginRequest = (url: string, method: string, params?: object) => {
    return serviceAxios({
        url: LOGIN_URL + url, method, ...params
    });
};
export const userLogin = (data: object) => {
    return loginRequest(`auth`, 'post', { data });
};
export const userRegister = (data: object) => {
    return loginRequest(`register`, 'post', { data });
};
export const userCollectList = () => {
    return loginRequest(`collectList`, 'get');
};
export const userLikeList = () => {
    return loginRequest(`likeList`, 'get');
};
export const userGetBaseInfo = (id: string) => {
    return loginRequest(`getBaseInfo?id=${id}`, 'get');
};

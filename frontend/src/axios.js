import axios from 'axios';
import router from './router.js';

const axiosInstance = axios.create({
  withCredentials: true,
  baseURL: process.env.VUE_APP_API_ORIGIN,
});

axiosInstance.interceptors.request.use(function (config) {
  config.headers = {
    Authorization:
      localStorage.getItem('token') != null
        ? `Bearer ${localStorage.getItem('token')}`
        : null,
    Accept: 'application/json',
  };
  return config;
});

axiosInstance.interceptors.response.use(
  function (response) {
    return response;
  },
  function (error) {
    console.log(error);
    switch (error.response.status) {
      case 400:
        return Promise.reject(error);
      case 401:
        return Promise.reject(error);
      case 403:
        console.log(error);
        router.replace('/auth');
        return Promise.reject(error);
      case 500:
        router.replace('/error');
        return Promise.reject(error);
      default:
        return Promise.reject(error);
    }
  }
);

export default axiosInstance;

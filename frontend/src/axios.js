import axios from 'axios';

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

export default axiosInstance;

import axios from "axios";

const axiosInstance = axios.create({
  withCredentials: true,
  baseURL: process.env.VUE_APP_API_ORIGIN,
});

axiosInstance.interceptors.request.use(function (config) {
  config.headers.common = {
    Authorization: `Bearer ${localStorage.getItem("authToken")}`,
    "Content-Type": "application/json",
    Accept: "application/json",
  };
  return config;
});

export default axiosInstance;

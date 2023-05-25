import axios from "axios";
import VueCookies from "vue-cookies";
// import { refreshToken } from "../service/login";

const instance = axios.create({
  baseURL: "http://192.168.210.61:8080",
});

// Add a request interceptor
instance.interceptors.request.use(
  async function (config) {
    config.headers.Authorization = "Bearer " + VueCookies.get("accessToken");

    console.log(config);
    return config;
  },
  function (error) {
    // Do something with request error
    return Promise.reject(error);
  }
);

// Add a response interceptor
instance.interceptors.response.use(
  function (response) {
    // Any status code that lie within the range of 2xx cause this function to trigger
    // Do something with response data
    return response;
  },
  async function (error) {
    // Any status codes that falls outside the range of 2xx cause this function to trigger
    // Do something with response error
    console.log("에러일 경우", error.config);
    const errorAPI = error.config;
    if (error.response.data.status === 401 && errorAPI.retry === undefined) {
      errorAPI.retry = true;
      console.log("토큰이 이상한 오류일 경우");
      // await refreshToken();
      return await axios(errorAPI);
    }
    return Promise.reject(error);
  }
);

export default instance;

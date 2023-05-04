import { createApp } from "vue";
import App from "./App.vue";
import router from "./router/router";
import axios from "axios";
import { gsap } from "gsap";
import { library } from "@fortawesome/fontawesome-svg-core";
import { fas } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";

library.add(fas);

const app = createApp(App);

// [axios / http 통신]
app.config.globalProperties.$axios = axios;

// 폰트 어썸
app.component("font-awesome-icon", FontAwesomeIcon);

// 라우터
app.use(router);

// content story 부분 라이브러리 -> gsap
app.use(gsap);

app.mount("#app");

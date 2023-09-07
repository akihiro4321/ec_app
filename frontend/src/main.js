import { createApp } from "vue";
import App from "./App.vue";
import axios from "./axios.js";
import router from "./router.js";
import store from "./store/index.js";

import BaseButton from "./components/ui/BaseButton.vue";

window.axios = axios;
const app = createApp(App);
// app.use(VueAxios, axios);
app.use(router);
app.use(store);

app.component("base-button", BaseButton);

app.mount("#app");

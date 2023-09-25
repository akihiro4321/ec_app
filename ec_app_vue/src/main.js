import { createApp } from 'vue';
import App from './App.vue';
import axios from './axios.js';
import router from './router.js';
import store from './store/index.js';

import BaseButton from './components/ui/BaseButton.vue';
import BaseCard from './components/ui/BaseCard.vue';
import BaseDialog from './components/ui/BaseDialog.vue';
import BaseModal from './components/ui/BaseModal.vue';
import SmallCard from './components/ui/SmallCard.vue';

window.axios = axios;
const app = createApp(App);
app.use(router);
app.use(store);

app.component('base-button', BaseButton);
app.component('base-card', BaseCard);
app.component('base-modal', BaseModal);
app.component('small-card', SmallCard);
app.component('base-dialog', BaseDialog);

app.mount('#app');

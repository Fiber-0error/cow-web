import { createApp } from 'vue';
import { createPinia } from 'pinia';

import ArcoVue from '@arco-design/web-vue';
import ArcoVueIcon from '@arco-design/web-vue/es/icon';
import App from './App.vue';
import '@arco-design/web-vue/dist/arco.css';
import 'ant-design-vue/dist/reset.css';
import router from './router';
import '@/assets/main.css';

const app = createApp(App);

app.use(createPinia());
app.use(router).use(ArcoVue).use(ArcoVueIcon);

app.mount('#app');
document.body.setAttribute('arco-theme', 'dark')

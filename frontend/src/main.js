import './assets/main.css'

import { createApp } from 'vue'
import { createRouter, createWebHistory } from 'vue-router'
import App from './App.vue'
import ImageUploader from './components/ImageUploader.vue'

const router = createRouter({
  routes: [{
    path: '/',
    component: ImageUploader,
  }],
  history: createWebHistory()
})

const app = createApp(App);
app.use(router);
app.mount('#app')

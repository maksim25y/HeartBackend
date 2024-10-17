import './assets/main.css'

import { createApp } from 'vue'
import { createRouter, createWebHistory } from 'vue-router'
import App from './App.vue'
import ImageList from './components/ImageList.vue'

const router = createRouter({
  routes: [{
    path: '/images',
    component: ImageList,
  }],
  history: createWebHistory()
})

const app = createApp(App);
app.use(router);
app.mount('#app')

import './assets/main.css'

import { createApp } from 'vue'
import { createRouter, createWebHistory } from 'vue-router'
import App from './App.vue'
import LoginWindow from './components/LoginWindow.vue'
import RegistrationWindow from './components/RegistrationWindow.vue'

const router = createRouter({
  routes: [
    {
    path: '/login',
    component: LoginWindow,
    },
    {
    path: '/registration',
    component: RegistrationWindow,
    }
  
],
  history: createWebHistory()
})

const app = createApp(App);
app.use(router);
app.mount('#app')

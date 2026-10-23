import { createApp } from 'vue'
import { createPinia } from 'pinia'
import './style.css'
import App from './App.vue'
import router from './router/index.js' // Importa as rotas

const app = createApp(App) // 1. Cria a instância e salva na variável 'app'
const pinia = createPinia()

app.use(router) // 2. Registra o router
app.use(pinia)
app.mount('#app') // 3. Monta a aplicação por último

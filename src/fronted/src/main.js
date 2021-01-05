import Vue from 'vue'
import App from './App.vue'
import Vuetify from 'vuetify'
import 'vuetify/dist/vuetify.min.css'
import { store } from './store'
import { router } from './routes'

Vue.use(Vuetify);
Vue.config.productionTip = false

new Vue({
  el: '#app',
  store,
  router,
  vuetify : new Vuetify(),
  render: h => h(App)
})

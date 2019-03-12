import Vue from 'vue'
import App from './App.vue'
import Router from'vue-router'

import ListarContatos from './components/ListarContatos.vue'
import Contato from './components/Contato.vue'

Vue.use(Router)
const router = new Router ({
  routes: [{
      path:'/',
      name:'listarContatos',
      component:ListarContatos
    },
    {
      path:'/contato',
      name:'contato',
      component:Contato,
      props:true
    },
    {
      path:'/contato/:id',
      name:'contato',
      component:Contato,
      props:true
    }
  ]
})

new Vue({
  el: '#app',
  render: h => h(App),
  router
})

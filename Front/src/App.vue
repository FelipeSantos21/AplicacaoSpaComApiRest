<template>
  <div id="app">
    <header>
      <h2>Cadastro e consulta de clientes</h2>
      <h5>Feito por: Felipe Augusto Pereira dos Santos</h5>
    </header>

    <main> 
      <div class="content">
        <keep-alive>
          <router-view ></router-view>
        </keep-alive>
      </div>
    </main>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'app',
  data () {
    return {
    }
  }  
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 50px;
}

h1, h5 {
  font-weight: normal;
  line-height: 0;
}

header {
  position: absolute;
  top: 0;
  width: 100%;
  min-height: 20px;
  text-align: center;
}

main {
  display: flex;
  margin-top: 20px;
  margin-left: auto;
  margin-right: auto;
  overflow: hidden;
}

.content {
  width: 100%;
  margin-top: 50px;
  display: flex;
  align-items: flex-start;
  justify-content: flex-start;
}
</style>

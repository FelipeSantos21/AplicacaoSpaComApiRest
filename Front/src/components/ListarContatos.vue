<template>
  <div class="container">
    <div id="topbar">
      <router-link to="/contato"><button>Adicionar Contato</button></router-link>
      <div id="searchbar"> 
        <span>Pesquisa:</span>
        <input v-model="pesquisa.nome" placeholder="Nome" class="input-search">
        <input v-model="pesquisa.sobrenome" placeholder="Sobrenome" class="input-search">
        <input v-model="pesquisa.telefone" placeholder="Telefone" class="input-search">
        <button @click="getAllContatos(true)">Buscar</button>
        <button @click="clearSearch()">Limpar</button>
      </div>
    </div>
    <table>
        <tr>
            <th @click="ordenar(0)" class="tb-header">Nome<span/></th>
            <th @click="ordenar(1)" class="tb-header">Sobrenome<span/></th>
            <th @click="ordenar(2)" class="tb-header">Email<span/></th>
            <th @click="ordenar(3)" class="tb-header">Telefone<span/></th>
            <th @click="ordenar(4)" class="tb-header">Twitter<span/></th>
            <th @click="ordenar(5)" class="tb-header">Skype<span/></th>
            <th></th>
        </tr>

        <tr v-for='contato in contatos' :key="contato.id" class="tb-row">
            <td class="nome">{{contato.nome}}</td>
            <td class="sobrenome">{{contato.sobrenome}}</td>
            <td class="email">{{contato.email}}</td>
            <td class="telefone">{{contato.telefone}}</td>
            <td class="twitter">{{contato.twitter}}</td>
            <td class="skype">{{contato.skype}}</td>
            <td class="editar"><router-link :to="{ name: 'contato', params: {id: contato.id}}"><button>Visualizar/Editar</button></router-link></td>
          </tr>
    </table>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'listarContatos',
  data () {
    return {
      pesquisa:{
        nome: "",
        sobrenome: "",
        telefone: ""
      },
      contatos: [],
      getAll: 'http://127.0.0.1:8090/all'
    }
  },

  mounted: function() {
    console.log("###### MOUNTED #######");
    // Mostra os contatos armazenados no banco de dados na tabela
    this.getAllContatos(false);
  },

  methods: {
    // Busca os contatos no banco de dados via API e atualiza a variavel que mostra eles na tela
    getAllContatos(search) {  
      let contatos = [];  
        axios.get(this.getAll)
        .then(response => {
          this.contatos = response.data;
          console.log("######## GET ALL CONTATOS #########");
          if (search) {
            this.searchContato();
          }
        }).catch(erro => {
          console.log("######## ERRO (Listar Contatos) ########");
          console.log(erro);
          alert("Não foi possível atualizar a lista de contatos.\nTente novamente mais tarde.")
        })     
    },
    
    // Faz uma busca nos contatos e mostra na tabela apenas os que estejam de acordo com a pesquisa
    searchContato: function () {
      let tam = this.contatos.length
      let newContatos = this.contatos;

      if (this.pesquisa.nome != "") {
        newContatos = [];
        for (let i = 0; i < this.contatos.length; i++) {
          if ((-1 != this.contatos[i].nome.toLowerCase().indexOf(this.pesquisa.nome.toLowerCase()))) {
            newContatos.push(this.contatos[i]);
          }
        }
        this.contatos = newContatos;
      }

      if (this.pesquisa.sobrenome != "") {
        newContatos = [];
        for (let i = 0; i < this.contatos.length; i++) {
          if ((-1 != this.contatos[i].sobrenome.toLowerCase().indexOf(this.pesquisa.sobrenome.toLowerCase()))) {
            newContatos.push(this.contatos[i]);
          }
        }
        this.contatos = newContatos;
      }

      if (this.pesquisa.telefone != "") {
        newContatos = [];
        for (let i = 0; i < this.contatos.length; i++) {
          if ((-1 != this.contatos[i].nome.toLowerCase().indexOf(this.pesquisa.nome.toLowerCase()))) {
            newContatos.push(this.contatos[i]);
          }
        }
        this.contatos = newContatos;
      }

      console.log("\n####### SEARCH ##########\n");
    },

    // Limpa os campos de busca e reinicia a tabela com todos os contatos
    clearSearch: function () {
      this.pesquisa = {
        nome: "",
        sobrenome: "",
        telefone: ""
      };
      this.getAllContatos(false);
    },

    // Faz a ordenação dos dados por meio do algoritmo Bubble Sort
    BubbleSort: function (linhas, colunaOrdem, crescente) {
      let tam = linhas.length;
      while (tam >= 0) {
        for (let i = 0; i < tam -1; i++) {
          if (crescente) { // Ordena de forma crescente
            if (linhas[i].children[colunaOrdem].innerHTML.toLowerCase() > linhas[i+1].children[colunaOrdem].innerHTML.toLowerCase()) {
              linhas[i].parentElement.insertBefore(linhas[i + 1], linhas[i]);
            }

          } else { // Ordena de forma decrescente
            if (linhas[i].children[colunaOrdem].innerHTML.toLowerCase() < linhas[i+1].children[colunaOrdem].innerHTML.toLowerCase()) {
              linhas[i].parentElement.insertBefore(linhas[i + 1], linhas[i]);
            }
          }
        }
        tam--;
      }
    },

    // Função chamada ao clicar em um cabeçalho da tabela para que ela fique ordenada
    ordenar: function(colunaOrdem) {
      let linhas = document.getElementsByClassName("tb-row");
      let cabecalhos = document.getElementsByClassName("tb-header");
      let crescente = !cabecalhos[colunaOrdem].classList.contains("crescente");
      console.log(cabecalhos[colunaOrdem]);
      
      for (let i = 0; i < cabecalhos.length; i++) {
        cabecalhos[i].classList.remove("crescente");
        cabecalhos[i].classList.remove("ordenado");
        cabecalhos[i].children[0].classList.remove("seta-baixo-icone");
        cabecalhos[i].children[0].classList.remove("seta-cima-icone");
      }
      if (crescente) {
        cabecalhos[colunaOrdem].classList.add("crescente");
        cabecalhos[colunaOrdem].children[0].classList.add("seta-baixo-icone");

      } else {
        cabecalhos[colunaOrdem].children[0].classList.add("seta-cima-icone");
      }
      cabecalhos[colunaOrdem].classList.add("ordenado");
      this.BubbleSort(linhas, colunaOrdem, crescente);
      
    }
  },
  watch: {
    '$route' () { // Função chamada ao mudar de tela         
      setTimeout(() => {
        this.getAllContatos(false);
      }, 500);
    }
  }
}
</script>

<style>
.container {
    width: 100%;
}

#topbar {
  display: flex;
  justify-content: space-between;
  margin-bottom: 1vh;
}

#searchbar {
  display: flex;
}

table {
  border-collapse: collapse;
  width: 100%;
}

td, th {
  border: 1px solid #a7a7a7;
  text-align: left;
  padding: 8px;
}

tr:nth-child(even) {
  background-color: #f1efef;
}

.ordenado {
  
  background-color: #00a0cd;
  color: white;
}

.seta-baixo-icone:before {
  align-self: right;
  content: "";
  display: inline-block;
  vertical-align: middle;

  border-left: 0.5vh solid transparent;
  border-right: 0.5vh solid transparent;
  border-top: 0.5vh solid;
}

.seta-cima-icone:before {
  align-self: right;
  content: "";
  display: inline-block;
  vertical-align: middle;

  border-left: 0.5vh solid transparent;
  border-right: 0.5vh solid transparent;
  border-bottom: 0.5vh solid;
}

a:link, a:visited, a:active, a:hover {
  color: black;
  font-style: normal;
  text-decoration: none;
}
</style>

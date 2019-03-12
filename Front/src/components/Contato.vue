<template>
  <div>
    <div id="topbar">
      <router-link to="/"><button>Voltar</button></router-link>
    </div>
    <img>
    <table>
      <tr>
        <td>Nome: </td>
        <td><input type="text" v-model="contato.nome"></td>
      </tr>
      <tr>
        <td>Sobrenome: </td>
        <td><input type="text" v-model="contato.sobrenome"></td>
      </tr>
      <tr>
        <td>Email: </td>
        <td><input type="text" v-model="contato.email"></td>
      </tr>
      <tr>
        <td>Telefone: </td>
        <td><input type="text" v-model="contato.telefone"></td>
      </tr>
      <tr>
        <td>Twitter: </td>
        <td><input type="text" v-model="contato.twitter"></td>
      </tr>
      <tr>
        <td>Skype: </td>
        <td><input type="text" v-model="contato.skype"></td>
      </tr>
    </table>
    <div> 
      <button @click="deletar()">{{estado.deletar}}</button>
      <button @click="aplicar()" type="submit" id="aplicar">{{estado.aplicar}}</button>
    </div>
    <div v-if="id"> <!-- Espera o props id estar pronto antes de gerar a pagina --> </div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'mostrarContato',
  props: ['id'],
  data () {
    return {
      contato: {
        nome:"",
        sobrenome: "",
        email: "",
        telefone: "",
        twitter: "",
        skype:""
      },
      estado: {
        deletar: "",
        aplicar: ""
      },
      urlAdicionar: "http://localhost:8090/add?",
      urlBuscarPorId: "http://localhost:8090/getbyid/",
      urlAlterar: "http://127.0.0.1:8090/update/",
      urlDeletar: "http://127.0.0.1:8090/deletebyid/"
    }
  },
  
  mounted () {
    this.inciarTela();
  },
  methods: {
    // Função que deixa a tela em seu estado inicial, ou limpa ou com os dados do contato passado
    inciarTela: function () {
      if (this.id == null) { // Modo de adicionar um usuario
        this.estado.aplicar = "Criar";
        this.estado.deletar = "Cancelar";
        this.contato = {
          nome:"",
          sobrenome: "",
          email: "",
          telefone: "",
          twitter: "",
          skype:""
        }

      } else { // Modo de alterar um usuario
        this.estado.aplicar = "Alterar";
        this.estado.deletar = "Deletar";
        axios.get(this.urlBuscarPorId+this.id)
        .then(response => {
          this.contato = response.data;
        }).catch(erro => {
          console.log("######## ERRO (Alterar Contatos) ########");
          console.log(erro);
        })
      }
    },

    // Função chamada ao clicar no botão Apllicar ou Alterar
    aplicar: function () {

      if (this.contato.nome == "" || this.contato.sobrenome == "" || this.contato.telefone == "") {
        alert("Os campos: Nome, Sobrenome e Telefone são obrigatórios.");
        return;
      }

      if (this.estado.aplicar == "Alterar") { // Alterar um usuario
        let contJson = {
          'nome': this.contato.nome,
          'sobrenome': this.contato.sobrenome,
          'telefone': this.contato.telefone,
          'email': this.contato.email,
          'twitter': this.contato.twitter,
          'skype': this.contato.skype,
          'localFoto': this.contato.localFoto
        }

        axios.put(this.urlAlterar+this.contato.id, JSON.stringify(contJson),{
          headers: {
            'Content-Type': 'application/json'
          }
        }).then(response => {
          console.log(response.data);
        }).catch(erro => {
          console.log("######## ERRO (Alterar Contatos) ########");
          console.log(erro);
        })

      } else { // Criar um usuario
        axios.get(this.urlAdicionar+
          "nome="+this.contato.nome+
          "&sobrenome="+this.contato.sobrenome+
          "&email="+this.contato.email+
          "&telefone="+this.contato.telefone+
          "&twitter="+this.contato.twitter+
          "&skype="+this.contato.skype+
          "&localFoto=")
        .then(response => {
          console.log(response.data);

        }).catch(erro => {
          console.log("######## ERRO (Alterar Contatos) ########");
          console.log(erro);
        })
      }
      this.$router.push("/");
    },

    // Função chamada ao clicar no botão deletar ou cancelar
    deletar: function () {
      if (this.estado.aplicar == "Alterar") { // Deleta um usuario
        axios.get(this.urlDeletar+this.contato.id).then(response => {
          console.log(response.data);
        }).catch(erro => {
          console.log("######## ERRO (Alterar Contatos) ########");
          console.log(erro);
        })

      }
      this.$router.push("/");
    }
  },
  watch: {
    '$route' (from, to) {
      // Caso mude de tela, ele reinicia o estado da tela
      this.inciarTela();
    }
  }
}

</script>

<style>
h1, h2 {
  font-weight: normal;
}
</style>

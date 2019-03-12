package eu.felipesantos.SpringMySql;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // Indica que a tabela vai ser feita a partir dessa entidade
public class Contato {
	// Configuracoes do id do banco de dados e as variaveis para a tabela
	@Id
    @GeneratedValue
    private Integer id;
    
    private String nome;
    private String sobrenome;
    private String email;
    private String telefone;
    private String twitter;
    private String skype;
    
    // Getters e Setters das variaveis a serem armazenadas no bd
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getTwitter() {
		return twitter;
	}
	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}
	public String getSkype() {
		return skype;
	}
	public void setSkype(String skype) {
		this.skype = skype;
	}    
}

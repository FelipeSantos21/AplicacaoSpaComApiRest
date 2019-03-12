package eu.felipesantos.SpringMySql;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import eu.felipesantos.SpringMySql.Contato;
import eu.felipesantos.SpringMySql.RepositorioContatos;

@RestController    // Indica que essa classe é o controlador
@CrossOrigin // Permite o acesso pelo axios a esta API

public class MainController {
	@Autowired
	
	private RepositorioContatos reContatos;

	// Função para cadastrar um contato
	@GetMapping(path="/add")
	public @ResponseBody String addNewContato (@RequestParam String nome, @RequestParam String sobrenome, @RequestParam String email, @RequestParam String telefone, @RequestParam String twitter, @RequestParam String skype) {

		Contato n = new Contato();
		n.setNome(nome);
		n.setSobrenome(sobrenome);
		n.setEmail(email);
		n.setTelefone(telefone);
		n.setTwitter(twitter);
		n.setSkype(skype);
		
		reContatos.save(n);
		return "ok";
	}

	// Busca todos os contatos registrados no banco de dados
	@GetMapping(path="/all")
	public @ResponseBody Iterable<Contato> getAllUsers() {
		return reContatos.findAll(); // Retorna em JSON
	}
	
	// Busca o contato via id
	@RequestMapping(value="/getbyid/{id}")
    public Contato getById( @PathVariable("id") int id){
        return reContatos.findById(id).get();
    }
    
	// Atualiza um contato
    @RequestMapping(value="/update/{id}", method=RequestMethod.PUT)
    public Contato updateEmployee(@PathVariable("id") int id, @RequestBody Contato cont){
    	
        Contato c = reContatos.findById(id).get();
        c.setNome(cont.getNome());
        c.setSobrenome(cont.getSobrenome());
        c.setEmail(cont.getEmail());
        c.setTelefone(cont.getTelefone());
        c.setTwitter(cont.getTwitter());
        c.setSkype(cont.getSkype());
        reContatos.save(c);
        return c;
    }
    
    // Deleta um contato usando o id
    @RequestMapping(value="/deletebyid/{id}")
    public String DeleteById( @PathVariable("id") int id){
        if (reContatos.existsById(id)) {
            reContatos.deleteById(id);
            return "ok";
        }
        
        return "Nao Encontrado";
    }
}
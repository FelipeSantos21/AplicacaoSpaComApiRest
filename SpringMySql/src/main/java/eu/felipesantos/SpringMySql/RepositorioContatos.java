package eu.felipesantos.SpringMySql;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import eu.felipesantos.SpringMySql.Contato;

// Interface que pergmite gerar o CRUD via CRUD Repository 
public interface RepositorioContatos extends CrudRepository<Contato, Integer> {
}
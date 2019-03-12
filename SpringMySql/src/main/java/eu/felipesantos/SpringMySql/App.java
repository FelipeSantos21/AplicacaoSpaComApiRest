package eu.felipesantos.SpringMySql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // Indica que é para auto configurar a aplicação Spring
public class App 
{
    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args); // Inicia a aplicação Spring
    }
}

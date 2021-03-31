package com.elcoma.api;
import com.elcoma.api.domain.Cliente;
import com.elcoma.api.domain.Usuario;
import com.elcoma.api.repositories.ClienteRepository;
import com.elcoma.api.services.ClienteService;
import com.elcoma.api.services.UsuarioService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App  {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}

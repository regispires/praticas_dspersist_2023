package br.ufc.quixada.ui;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("br.ufc.quixada.entity")
@EnableJpaRepositories("br.ufc.quixada.dao")
public class Principal {

	public static void main(String[] args) {
		//SpringApplication.run(InsereClientes.class, args);
		//SpringApplication.run(InsereProdutos.class, args);
		//SpringApplication.run(InsereCompras.class, args);
		//SpringApplication.run(ListaProdutos.class, args);
		//SpringApplication.run(ListaClientes.class, args);

		SpringApplicationBuilder builder = new SpringApplicationBuilder(CRUDClientes.class);
		builder.headless(false).run(args);
	}
	
}

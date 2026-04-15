package com.example.algamoney.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.example.algamoney.api.config.property.AlgamoneyApiProperty;

@SpringBootApplication // Anotação para indicar que esta é a classe principal de uma aplicação Spring Boot
@EnableConfigurationProperties(AlgamoneyApiProperty.class)
public class AlgamoneyApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlgamoneyApiApplication.class, args);
	}
}

// Nesse código, a classe AlgamoneyApiApplication é a classe principal da aplicação Spring Boot. 
// A anotação @SpringBootApplication é uma combinação de três anotações:
// @Configuration: Indica que a classe pode ser usada pelo Spring IoC container como fonte de definições de beans.
// (beans são objetos gerenciados pelo Spring, que podem ser injetados em outras partes da aplicação
// para promover a inversão de controle e facilitar a modularização da aplicação)
// @EnableAutoConfiguration: Habilita a configuração automática do Spring Boot com base nas 
// dependências presentes no classpath da aplicação)
// @ComponentScan: Habilita a varredura de componentes, permitindo que o Spring encontre 
// e registre os beans definidos em outras classes da aplicação (como controladores, serviços, repositórios, etc.).
// O método main é o ponto de entrada da aplicação, onde a classe SpringApplication é usada para iniciar
// a aplicação Spring Boot, passando a classe AlgamoneyApiApplication como argumento para configurar a aplicação.

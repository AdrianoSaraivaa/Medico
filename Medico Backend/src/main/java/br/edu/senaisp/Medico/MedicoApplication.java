package br.edu.senaisp.Medico; // Declara o pacote onde esta classe está localizada

import org.springframework.boot.SpringApplication; // Importa a classe SpringApplication do Spring Boot, usada para iniciar a aplicação
import org.springframework.boot.autoconfigure.SpringBootApplication; // Importa a anotação SpringBootApplication, que configura a aplicação Spring Boot

@SpringBootApplication // Anotação que combina @Configuration, @EnableAutoConfiguration e @ComponentScan. Marca esta classe como a principal de configuração para uma aplicação Spring Boot
public class MedicoApplication { // Declaração da classe pública MedicoApplication

    public static void main(String[] args) { // Método principal que é o ponto de entrada da aplicação
        SpringApplication.run(MedicoApplication.class, args); // Método estático run da classe SpringApplication é chamado para iniciar a aplicação Spring Boot
    }
}

package br.edu.senaisp.Medico.config; // Declara o pacote onde esta classe está localizada

import org.springframework.context.annotation.Bean; // Importa a anotação @Bean, que indica que um método produz um bean gerenciado pelo Spring
import org.springframework.context.annotation.Configuration; // Importa a anotação @Configuration, que indica que a classe pode ser usada pelo Spring IoC container como uma fonte de definições de beans
import org.springframework.web.servlet.config.annotation.CorsRegistry; // Importa a classe CorsRegistry, usada para adicionar mapeamentos de CORS
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer; // Importa a interface WebMvcConfigurer, que fornece métodos de callback para personalizar a configuração do Spring MVC

@Configuration // Anotação que indica que esta classe pode ser usada pelo Spring IoC container como uma fonte de definições de beans
public class CorsConfig { // Declaração da classe pública CorsConfig

    @Bean // Anotação que indica que o método produz um bean gerenciado pelo Spring
    public WebMvcConfigurer corsConfigurer() { // Método que retorna uma implementação de WebMvcConfigurer para configurar CORS
        return new WebMvcConfigurer() { // Retorna uma nova instância anônima de WebMvcConfigurer
            @Override // Anotação que indica que estamos sobrescrevendo o método da interface WebMvcConfigurer
            public void addCorsMappings(CorsRegistry registry) { // Método para adicionar mapeamentos de CORS
                registry.addMapping("/**") // Permite solicitações de qualquer caminho
                        .allowedOrigins("http://localhost:3000") // Permite solicitações de um determinado domínio (http://localhost:3000)
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Permite os métodos HTTP especificados
                        .allowedHeaders("*") // Permite todos os cabeçalhos
                        .allowCredentials(true); // Permite o envio de credenciais
            }
        };
    }
}

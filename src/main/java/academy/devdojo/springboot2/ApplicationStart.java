package academy.devdojo.springboot2.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

// Bean de configuração inicialç do Spring
@EnableAutoConfiguration
// Utilizando quando se tem subniveis de pastas
@ComponentScan(basePackages = "academy.devdojo.springboot2")
public class ApplicationStart {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationStart.class, args);
    }

}

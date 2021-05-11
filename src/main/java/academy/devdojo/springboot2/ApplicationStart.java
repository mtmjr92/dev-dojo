package academy.devdojo.springboot2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
  Bean de configuração inicial do Spring
*/
//@EnableAutoConfiguration

/*
  Utilizando quando se tem subniveis de pastas
  @ComponentScan(basePackages = "academy.devdojo.springboot2")
*/
//@ComponentScan

/*
  Transforma sua classe em um Bean (Sacaneavel pelo Spring)
  Esta casse vai para o Filter Chain => Corrente de filtros
*/
//@Configuration

/**
 * Esta configuração substitui todas os outros beans
 * @ EnableAutoConfiguration
 * @ ComponentScan
 * @ Configuration
 */
@SpringBootApplication
public class ApplicationStart {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationStart.class, args);
    }

}

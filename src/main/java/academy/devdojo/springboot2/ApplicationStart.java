package academy.devdojo.springboot2;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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

    @Bean
    MeterRegistryCustomizer<MeterRegistry> configurer(
            @Value("${spring.application.name}") String applicationName) {
        return (registry) -> registry.config().commonTags("application", applicationName);
    }

}

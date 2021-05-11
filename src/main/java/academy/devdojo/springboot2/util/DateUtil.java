package academy.devdojo.springboot2.util;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// necessario para a injeção de dependencia via @Autowired
@Component
public class DateUtil {

    public String formatLocalDateTimeToDataBaseStyle(LocalDateTime localDateTime) {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(localDateTime);
    }

}

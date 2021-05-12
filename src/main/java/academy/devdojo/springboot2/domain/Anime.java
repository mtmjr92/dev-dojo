package academy.devdojo.springboot2.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

// gets, sets, equals, toString
@Data
// construtor com todos os valores
@AllArgsConstructor
public class Anime {

    private Long id;
    private String name;

}

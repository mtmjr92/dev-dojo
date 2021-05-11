package academy.devdojo.springboot2.controller;

import academy.devdojo.springboot2.domain.Anime;
import academy.devdojo.springboot2.util.DateUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("anime")
@Log4j2
public class AnimeController {

    /**
     * Esta injeção de Campos não é recomendada
     * - Utilizar um construtor
     * - @RequiredArgsConstructor - via lombok cria construtor com campos finais
     * - @AllArgsConstructor - via lombok cria construtor com todos os campos
     * - Forma mais correta é criar uma interface
     */
    @Autowired
    private DateUtil dateUtil;

    /**
     * Endpoit para listar os Animes
     * @return retorna uma lista de Animes
     */
    @GetMapping(path = "list")
    public List<Anime> list() {
        log.info(dateUtil.formatLocalDateTimeToDataBaseStyle(LocalDateTime.now()));
        return List.of(new Anime("DBZ"), new Anime("Bersek"));
    }

}

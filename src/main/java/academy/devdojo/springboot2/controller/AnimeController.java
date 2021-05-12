package academy.devdojo.springboot2.controller;

import academy.devdojo.springboot2.domain.Anime;
import academy.devdojo.springboot2.service.AnimeService;
import academy.devdojo.springboot2.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("animes")
@Log4j2
@RequiredArgsConstructor
public class AnimeController {

    /**
     * Esta injeção @Autowired de Campos não é recomendada
     * - Utilizar um construtor
     * - @RequiredArgsConstructor - via lombok cria construtor com campos finais
     * - @AllArgsConstructor - via lombok cria construtor com todos os campos
     * - Forma mais correta é criar uma interface
     */
    private final DateUtil dateUtil;

    private final AnimeService animeService;

    /**
     * Retorna lista de animes
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Anime>> lists() {
        log.info(dateUtil.formatLocalDateTimeToDataBaseStyle(LocalDateTime.now()));
        return new ResponseEntity<>(animeService.listAll(), HttpStatus.OK);
    }

    /**
     * Endpoit para listar os Animes
     * @return retorna uma lista de Animes
     */
    @GetMapping(path = "list")
    public List<Anime> list() {
        log.info(dateUtil.formatLocalDateTimeToDataBaseStyle(LocalDateTime.now()));
        return animeService.listAll();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Anime> findById(@PathVariable("id") Long id) {
        log.info("Endpoit por ID => {}", id);
        return ResponseEntity.ok(animeService.findById(id));
    }

    @PostMapping
    /*
        Pode-se utilizar a notação para retornar um status
    */
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Anime> save(@RequestBody Anime anime) {
        log.info("Endpoit POST => {}", anime);
        return new ResponseEntity<>(animeService.save(anime), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Anime> delete(@PathVariable Long id) {
        log.info("Endpoit DELETE => {}", id);
        animeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody Anime anime) {
        log.info("Endpoit PUT => {}", anime);
        animeService.replace(anime);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

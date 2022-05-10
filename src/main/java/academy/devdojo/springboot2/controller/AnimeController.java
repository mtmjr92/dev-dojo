package academy.devdojo.springboot2.controller;

import academy.devdojo.springboot2.domain.Anime;
import academy.devdojo.springboot2.requests.AnimePostRequestBody;
import academy.devdojo.springboot2.requests.AnimePutRequestBody;
import academy.devdojo.springboot2.service.AnimeService;
import academy.devdojo.springboot2.util.DateUtil;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
     * @return retorna lista completa de animes
     */
    @GetMapping
    public ResponseEntity<Page<Anime>> lists(@ParameterObject Pageable pageable) {
        log.info(dateUtil.formatLocalDateTimeToDataBaseStyle(LocalDateTime.now()));
        return new ResponseEntity<>(animeService.listAll(pageable), HttpStatus.OK);
    }

    /**
     * Endpoit para listar os Animes
     * @return retorna uma lista de Animes
     */
    @GetMapping(path = "all")
    public List<Anime> listAll() {
        log.info(dateUtil.formatLocalDateTimeToDataBaseStyle(LocalDateTime.now()));
        return animeService.listAllNonPageable();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Anime> findById(@PathVariable("id") Long id) {
        log.info("Endpoit por ID => {}", id);
        return ResponseEntity.ok(animeService.findByIdOrThrowBadRequestException(id));
    }

    @GetMapping(path = "by-id/{id}")
    public ResponseEntity<Anime> findByIdAuthenticationPrincipal(@PathVariable("id") Long id,
                                                                 @AuthenticationPrincipal UserDetails userDetails) {
        log.info("Endpoit por ID UserDetais => {}", userDetails);
        return ResponseEntity.ok(animeService.findByIdOrThrowBadRequestException(id));
    }

    @GetMapping(path = "/find")
    public ResponseEntity<List<Anime>> findByName(@RequestParam(required = false, defaultValue = "") String name) {
        log.info("Endpoit por NAME => {}", name);
        return ResponseEntity.ok(animeService.findByName(name));
    }


    /*
        Pode-se utilizar a notação para retornar um status
    */
    //@ResponseStatus(HttpStatus.CREATED)
    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Anime> save(@RequestBody @Valid AnimePostRequestBody anime) {
        log.info("Endpoit POST => {}", anime);
        return new ResponseEntity<>(animeService.save(anime), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "admin/{id}")
    public ResponseEntity<Anime> delete(@PathVariable Long id) {
        log.info("Endpoit DELETE => {}", id);
        animeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody AnimePutRequestBody animePutRequestBody) {
        log.info("Endpoit PUT => {}", animePutRequestBody);
        animeService.replace(animePutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

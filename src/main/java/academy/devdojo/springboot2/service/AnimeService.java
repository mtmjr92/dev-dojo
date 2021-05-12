package academy.devdojo.springboot2.service;

import academy.devdojo.springboot2.domain.Anime;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/* Regras de negócio */
@Service
public class AnimeService {

    private List<Anime> animes =  new ArrayList<>(List.of(new Anime( 1L, "DBZ"), new Anime(2L,"Bersek")));

    public List<Anime> listAll() {
        return animes;
    }

    public Anime findById(Long id) {
        return animes.stream()
                .filter(x -> x.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "ANIME NOT FOUND"));
    }

    public Anime save(Anime anime ) {
        anime.setId(ThreadLocalRandom.current().nextLong(3, 100));
        animes.add(anime);
        return anime;
    }

}

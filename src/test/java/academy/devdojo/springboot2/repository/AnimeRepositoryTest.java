package academy.devdojo.springboot2.repository;

import academy.devdojo.springboot2.domain.Anime;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@DisplayName("Tests for Anime Repository")
class AnimeRepositoryTest {

    @Autowired
    private AnimeRepository animeRepository;

    @Test
    @DisplayName("Save creates when Successful")
    public void save_PersistAnime_WhenSuccessful() {
        Anime anime = createAnime();
        Anime save = this.animeRepository.save(anime);
        Assertions.assertThat(save).isNotNull(); // certifique que
        Assertions.assertThat(save.getId()).isNotNull();
        Assertions.assertThat(save.getName()).isEqualTo(anime.getName());
    }

    private Anime createAnime() {
        return Anime.builder()
                .name("Hajime no Ippo")
                .build();
    }

}
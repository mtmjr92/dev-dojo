package academy.devdojo.springboot2.repository;

import academy.devdojo.springboot2.domain.Anime;
import academy.devdojo.springboot2.utils.AnimeCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.Constraint;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@DisplayName("Tests for Anime Repository")
class AnimeRepositoryTest {

    @Autowired
    private AnimeRepository animeRepository;

    @Test
    @DisplayName("Save creates when Successful")
    public void save_PersistAnime_WhenSuccessful() {
        Anime anime = AnimeCreator.createAnimeToBeSaved();
        Anime save = this.animeRepository.save(anime);
        Assertions.assertThat(save).isNotNull(); // certifique que
        Assertions.assertThat(save.getId()).isNotNull();
        Assertions.assertThat(save.getName()).isEqualTo(anime.getName());
    }

    @Test
    @DisplayName("Save updates when Successful")
    public void save_UpdatesAnime_WhenSuccessful() {
        Anime anime = AnimeCreator.createAnimeToBeSaved();
        Anime save = this.animeRepository.save(anime);
        save.setName("Overlord");
        Anime animeUpdated = this.animeRepository.save(save);

        Assertions.assertThat(animeUpdated).isNotNull();
        Assertions.assertThat(animeUpdated.getId()).isNotNull();
        Assertions.assertThat(animeUpdated.getName()).isEqualTo(save.getName());
    }

    @Test
    @DisplayName("Delete removes when Successful")
    public void delete_RemovesAnime_WhenSuccessful() {
        Anime animeToBeSaved = AnimeCreator.createAnimeToBeSaved();
        Anime animeSaved = this.animeRepository.save(animeToBeSaved);

        this.animeRepository.delete(animeSaved);
        Optional<Anime> animeOptional = this.animeRepository.findById(animeSaved.getId());

        Assertions.assertThat(animeOptional).isEmpty();
    }

    @Test
    @DisplayName("Find By Name returns list of animes when Successful")
    public void findByName_RetunrsListOfAnime_WhenSuccessful() {
        Anime animeToBeSaved = AnimeCreator.createAnimeToBeSaved();
        Anime animeSaved = this.animeRepository.save(animeToBeSaved);

        String name = animeSaved.getName();
        List<Anime> animes = this.animeRepository.findByName(name);

        Assertions.assertThat(animes).isNotEmpty();
        Assertions.assertThat(animes).contains(animeSaved);
    }

    @Test
    @DisplayName("Save throw ConstraintViolationException when name is empty")
    public void save_ConstraintViolationException_WhenNameIsEmpty() {
        Anime anime = new Anime();

        Assertions.assertThatThrownBy(() -> this.animeRepository.save(anime))
                .isInstanceOf(ConstraintViolationException.class);
    }

}
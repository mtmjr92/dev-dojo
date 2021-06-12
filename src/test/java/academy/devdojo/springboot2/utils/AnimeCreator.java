package academy.devdojo.springboot2.utils;

import academy.devdojo.springboot2.domain.Anime;

public class AnimeCreator {

    public static Anime createAnimeToBeSaved() {
        return Anime.builder()
                .name("Hajime no Ippo")
                .build();
    }

    public static Anime createAnimeValidAnime() {
        return Anime.builder()
                .name("Hajime no Ippo")
                .id(1L)
                .build();
    }

    public static Anime createAnimeValidUpdatedAnime() {
        return Anime.builder()
                .name("Hajime no Ippo2")
                .id(1L)
                .build();
    }

}

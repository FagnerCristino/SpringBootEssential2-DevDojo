package com.fagnerdev.springboot2.util;

import com.fagnerdev.springboot2.domain.Anime;

public class AnimeCreator {

    public static Anime createAnimeToBeSaved(){
        return Anime.builder()
                .name("Hajjime no ippo")
                .build();
    }

    public static Anime createValidAnime(){
        return Anime.builder()
                .name("Hajjime no ippo")
                .id(1L)
                .build();
    }
    public static Anime createValidUpdatedAnime(){
        return Anime.builder()
                .name("Hajjime no ippo 2")
                .build();
    }
}

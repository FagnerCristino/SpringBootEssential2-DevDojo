package com.fagnerdev.springboot2.util;

import com.fagnerdev.springboot2.domain.Anime;
import com.fagnerdev.springboot2.requests.AnimePostRequestBody;

public class AnimePostRequestBodyCreator {

    public static AnimePostRequestBody createAnimePostRequestBody(){
        return AnimePostRequestBody.builder()
                .name(AnimeCreator.createAnimeToBeSaved().getName())
                .build();
    }
}

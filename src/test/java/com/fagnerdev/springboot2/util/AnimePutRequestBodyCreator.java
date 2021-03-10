package com.fagnerdev.springboot2.util;

import com.fagnerdev.springboot2.requests.AnimePutRequestBody;

public class AnimePutRequestBodyCreator {

    public static AnimePutRequestBody createAnimePutRequestBody(){
        return AnimePutRequestBody.builder()
                .name(AnimeCreator.createAnimeToBeSaved().getName())
                .build();
    }
}

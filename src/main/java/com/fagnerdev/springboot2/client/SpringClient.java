package com.fagnerdev.springboot2.client;


import com.fagnerdev.springboot2.domain.Anime;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

import static org.springframework.http.HttpMethod.*;

@Log4j2
public class SpringClient {

    public static void main(String[] args) {
        ResponseEntity<Anime> entity = new RestTemplate().getForEntity("http://localhost:8080/animes/{id}", Anime.class,
                2,5,6,3,7);
        log.info(entity);

        Anime[] animes = new RestTemplate().getForObject("http://localhost:8080/animes/listAll", Anime[].class);

        log.info(Arrays.toString(animes));

        //@formatter:off
        ResponseEntity<List<Anime>> exchange = new RestTemplate().exchange("http://localhost:8080/animes/listAll",
                GET,null,
                new ParameterizedTypeReference<>() {});
        //@formatter:on
        log.info(exchange.getBody());

        /*Anime kingdom = Anime.builder().name("kingdom").build();
        Anime animeSaved = new RestTemplate().postForObject("http://localhost:8080/animes/", kingdom, Anime.class);
        log.info("Saved anime {}", animeSaved);*/

        Anime samuraiChamploo = Anime.builder().name("Samurai Champloo").build();
        ResponseEntity<Anime> samuraiChamplooSaved = new RestTemplate().exchange("http://localhost:8080/animes/",
                POST,
                new HttpEntity<>(samuraiChamploo, createJsonHeader()),
                Anime.class);

        log.info("Saved anime {}", samuraiChamplooSaved);

        Anime animeToBeUpdated = samuraiChamplooSaved.getBody();
        animeToBeUpdated.setName("Samurai Champloo 2");

        ResponseEntity<Void> samuraiChamplooUpdated = new RestTemplate().exchange("http://localhost:8080/animes/",
                PUT, new HttpEntity<>(animeToBeUpdated, createJsonHeader()),
                Void.class);
        log.info(samuraiChamplooUpdated);

        ResponseEntity<Void> samuraiChamplooDeleted = new RestTemplate().exchange("http://localhost:8080/animes/{id}",
                DELETE, null,
                Void.class,
                animeToBeUpdated.getId());

        log.info(samuraiChamplooDeleted);
    }

    private static HttpHeaders createJsonHeader(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }
}

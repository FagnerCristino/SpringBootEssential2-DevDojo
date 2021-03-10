package com.fagnerdev.springboot2.repository;

import com.fagnerdev.springboot2.domain.Anime;
import com.fagnerdev.springboot2.util.AnimeCreator;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;


@DataJpaTest
@DisplayName("Test for Anime Respository")
@Log4j2
class AnimeRepositoryTest {

    @Autowired
    private AnimeRepository animeRepository;

    @Test
    @DisplayName("(Save) Persists Anime when Successful")
    void Save_persist_anime_when_successful(){

        Anime createAnimeToBeSaved = AnimeCreator.createAnimeToBeSaved();
        Anime animeSaved= this.animeRepository.save(createAnimeToBeSaved);
        Assertions.assertThat(animeSaved).isNotNull();
        Assertions.assertThat(animeSaved.getId()).isNotNull();
        Assertions.assertThat(animeSaved.getName()).isEqualTo(createAnimeToBeSaved.getName());
    }

    @Test
    @DisplayName("Saves updates Anime when Successful")
    void Save_updates_anime_when_successful(){
        Anime createAnimeToBeSaved = AnimeCreator.createAnimeToBeSaved();
        
        Anime animeSaved= this.animeRepository.save(createAnimeToBeSaved);
        
        animeSaved.setName("Overlord");

        Anime animeUpdated = this.animeRepository.save(animeSaved);

        Assertions.assertThat(animeUpdated).isNotNull();
        
        Assertions.assertThat(animeUpdated.getId()).isNotNull();
        
        Assertions.assertThat(animeUpdated.getName()).isEqualTo(animeSaved.getName());
    }

    @Test
    @DisplayName("Delete, removes Anime when Successful")
    void Delete_removes_anime_when_successful(){
        Anime createAnimeToBeSaved = AnimeCreator.createAnimeToBeSaved();

        Anime animeSaved= this.animeRepository.save(createAnimeToBeSaved);

        this.animeRepository.delete(animeSaved);

        Optional<Anime> animeOptional = this.animeRepository.findById(animeSaved.getId());
        Assertions.assertThat(animeOptional).isEmpty();


    }

    @Test
    @DisplayName("Find by name returns list of anime when Successful")
    void Find_by_name_returns_list_of_anime_when_successful(){
        Anime createAnimeToBeSaved = AnimeCreator.createAnimeToBeSaved();

        Anime animeSaved= this.animeRepository.save(createAnimeToBeSaved);

        String name = animeSaved.getName();

        List<Anime> animeList = this.animeRepository.findByName(name);

        Assertions.assertThat(animeList).isNotEmpty().contains(animeSaved);

    }

    @Test
    @DisplayName("Find by name returns empty list when no anime found")
    void find_by_name_returns_empty_list_when_anime_is_not_found(){
        List<Anime> animeList = this.animeRepository.findByName("aloka");

        Assertions.assertThat(animeList).isEmpty();


    }

    @Test
    @DisplayName("(Save) throw ConstraintViolationException when name is empty")
    void Save_throws_constrain_violation_exception_when_name_is_empty(){

        Anime anime = new Anime();
/*       Assertions.assertThatThrownBy(()-> this.animeRepository.save(anime))
               .isInstanceOf(ConstraintViolationException.class);*/

        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(()-> this.animeRepository.save(anime))
                .withMessageContaining("The anime name cannot be empty");

    }



}
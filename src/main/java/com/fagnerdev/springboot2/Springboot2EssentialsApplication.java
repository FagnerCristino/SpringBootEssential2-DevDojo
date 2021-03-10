package com.fagnerdev.springboot2;

import com.fagnerdev.springboot2.domain.Anime;
import com.fagnerdev.springboot2.repository.AnimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class Springboot2EssentialsApplication implements CommandLineRunner {

	@Autowired
	private AnimeRepository animeRepository;

	public static void main(String[] args) {
		SpringApplication.run(Springboot2EssentialsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Anime anime1 = new Anime(null, "DBZ");
		Anime anime2 = new Anime(null, "Sakura");
		Anime anime3 = new Anime(null, "Pokemon");
		Anime anime4 = new Anime(null, "Digimon");
		Anime anime5 = new Anime(null, "Caballeros del Zoodiaco");
		Anime anime6 = new Anime(null, "X-men");
		Anime anime7 = new Anime(null, "Street Fighters");
		Anime anime8 = new Anime(null, "Naruto");
		Anime anime9 = new Anime(null, "Avatar");
		Anime anime10 = new Anime(null, "Dead Note");
		Anime anime11 = new Anime(null, "Hantaro");
		Anime anime12 = new Anime(null, "Shakira");

		animeRepository.saveAll(Arrays.asList(anime1, anime2, anime3, anime4, anime5, anime6, anime7, anime8, anime9, anime10, anime11,
				anime12));


	}
}

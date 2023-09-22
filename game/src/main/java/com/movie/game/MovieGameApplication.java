package com.movie.game;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class MovieGameApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieGameApplication.class, args);
	}

}

package com.example.concert;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class ConcertApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConcertApplication.class, args);
	}

	@Bean
	CommandLineRunner init(){
		return args ->
				log.info("Successfully started");
	}
}

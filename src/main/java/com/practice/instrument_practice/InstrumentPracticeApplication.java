package com.practice.instrument_practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.practice.instrument_practice")
@EntityScan("com.practice.instrument_practice")
@SpringBootApplication
public class InstrumentPracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(InstrumentPracticeApplication.class, args);
	}

}

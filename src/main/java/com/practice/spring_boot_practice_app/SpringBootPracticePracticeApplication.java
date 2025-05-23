package com.practice.spring_boot_practice_app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class SpringBootPracticePracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootPracticePracticeApplication.class, args);
	}

}

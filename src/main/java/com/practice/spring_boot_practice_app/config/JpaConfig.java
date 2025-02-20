package com.practice.spring_boot_practice_app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.practice.spring_boot_practice_app")
public class JpaConfig {}

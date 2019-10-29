package com.example.boot;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/*
 * Copyright (C) 2019 by GMO Runsystem Company
 * Create  Application class
 * @version 1.0
 * @author ToanLM
 */

@SpringBootApplication
@ComponentScan("com.example")
@EntityScan("com.example.entity")
@EnableJpaRepositories(basePackages = {"com.example.repository"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}

package com.flab.eattofit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class EattofitApplication {

	public static void main(String[] args) {
		SpringApplication.run(EattofitApplication.class, args);
	}

}

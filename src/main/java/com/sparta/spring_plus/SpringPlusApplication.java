package com.sparta.spring_plus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringPlusApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringPlusApplication.class, args);
	}

}

package com.ift.mentoring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.ift.mentoring.repository")
@EntityScan(basePackages = "com.ift.mentoring.model")
public class MentoringApplication {

	public static void main(String[] args) {
		SpringApplication.run(MentoringApplication.class, args);
	}

}

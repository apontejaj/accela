package com.apontejaj.accela;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.apontejaj.entities")
@EnableJpaRepositories("com.apontejaj.repositories")
public class AccelaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccelaApplication.class, args);
	}

}

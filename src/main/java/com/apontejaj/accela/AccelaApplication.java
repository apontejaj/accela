package com.apontejaj.accela;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.apontejaj.entities")
public class AccelaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccelaApplication.class, args);
	}

}

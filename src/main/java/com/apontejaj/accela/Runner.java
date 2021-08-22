package com.apontejaj.accela;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class Runner implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(Runner.class);


	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		logger.info("App running");
		
	}
}
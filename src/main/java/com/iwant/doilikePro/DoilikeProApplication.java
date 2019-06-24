package com.iwant.doilikePro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DoilikeProApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoilikeProApplication.class, args);
	}

}

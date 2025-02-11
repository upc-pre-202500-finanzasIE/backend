package com.verano.finanzasingenieriabackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FinanzasingenieriabackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinanzasingenieriabackendApplication.class, args);
	}

}

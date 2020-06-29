package com.hiberus.bill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.hiberus.commons.model")
public class HiberusBillServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HiberusBillServiceApplication.class, args);
	}

}

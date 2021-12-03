package com.pet.dostavochka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class DostavochkaApplication {
	public static void main(String[] args) {
		SpringApplication.run(DostavochkaApplication.class, args);
	}
}

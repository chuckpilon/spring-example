package com.pilon.example.item;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@Configuration
@ImportResource("classpath:integration-context.xml")
public class ItemService {

	public static void main(String[] args) {
		SpringApplication.run(ItemService.class, args);
	}

}

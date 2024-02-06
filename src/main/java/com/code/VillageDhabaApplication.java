package com.code;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.code.repositories")
public class VillageDhabaApplication {

	public static void main(String[] args) {
		SpringApplication.run(VillageDhabaApplication.class, args);
	}

}

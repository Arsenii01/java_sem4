package com.example.pw17;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(
	exclude = { DataSourceAutoConfiguration.class }
)
public class Pw17Application {

	public static void main(String[] args) {
		SpringApplication.run(Pw17Application.class, args);
	}
}

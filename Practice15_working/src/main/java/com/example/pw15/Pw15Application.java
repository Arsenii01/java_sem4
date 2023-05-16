package com.example.pw15;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(
	exclude = { DataSourceAutoConfiguration.class }
)
public class Pw15Application {

	public static void main(String[] args) {
		SpringApplication.run(Pw15Application.class, args);
	}
}

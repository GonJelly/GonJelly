package com.example.google;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class GoogleApplication {

	private static final String properties = ""
		+ "spring.config.location="
		+ "classpath:/application.yml"
		+ ", classpath:/application-oAuth.yml";

	public static void main(String[] args) {
		new SpringApplicationBuilder(GoogleApplication.class)
			.properties(properties)
			.run(args);
	}

}

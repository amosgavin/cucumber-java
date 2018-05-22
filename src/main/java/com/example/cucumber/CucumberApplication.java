package com.example.cucumber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
@EnableConfigurationProperties
public class CucumberApplication {

	public static void main(String[] args) {
		SpringApplication.run(CucumberApplication.class, args);
	}
}

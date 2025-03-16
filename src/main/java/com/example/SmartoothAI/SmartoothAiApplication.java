package com.example.SmartoothAI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.SmartoothAI")
public class SmartoothAiApplication {
	public static void main(String[] args) {
		SpringApplication.run(SmartoothAiApplication.class, args);
	}
}

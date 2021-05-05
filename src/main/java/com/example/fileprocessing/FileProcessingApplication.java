package com.example.fileprocessing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.fileprocessing.controller","com.example.feeds.service",
								"com.example.fileprocessing.entity","com.example.fileprocessing.repo"})
public class FileProcessingApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileProcessingApplication.class, args);
	}

}

package com.example.filehandling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FilehandlingApplication {

	public static void main(String[] args) {

		System.out.println("Started the server");
		SpringApplication.run(FilehandlingApplication.class, args);
	}

}

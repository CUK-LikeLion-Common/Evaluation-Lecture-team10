package com.example.lectureevaluationdev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.example.lectureevaluationdev.entity")
public class LectureevaluationdevApplication {
	public static void main(String[] args) {
		SpringApplication.run(LectureevaluationdevApplication.class, args);
	}
}

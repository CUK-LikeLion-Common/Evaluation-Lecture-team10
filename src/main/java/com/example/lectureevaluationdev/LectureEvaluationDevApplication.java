package com.example.lectureevaluationdev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@EntityScan("com.example.lectureevaluationdev.entity")
public class LectureEvaluationDevApplication {
    public static void main(String[] args) {
        SpringApplication.run(LectureEvaluationDevApplication.class, args);
    }
}
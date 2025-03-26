package com.example.msumico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MsUmicoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsUmicoApplication.class, args);
    }

}

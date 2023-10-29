package com.example.gasip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GasipApplication {

    public static void main(String[] args) {
        SpringApplication.run(GasipApplication.class, args);
    }

}

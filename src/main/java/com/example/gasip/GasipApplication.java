package com.example.gasip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // JPA Auditing 어노테이션 모두 활성화
public class GasipApplication {

    public static void main(String[] args) {
        SpringApplication.run(GasipApplication.class, args);
    }

}
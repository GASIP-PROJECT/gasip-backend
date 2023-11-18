package com.example.gasip.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(title = "Gasip 서비스 API 명세서",
                description = "Gasip 교수평가 서비스 API 명세서",
                version = "v1.0.0"))
@RequiredArgsConstructor
@Configuration
public class swaggerConfig {
    @Bean
    public GroupedOpenApi professorOpenApi() {
        String[] paths = {"/all-professors/**"};

        return GroupedOpenApi.builder()
                .group("professors")
                .pathsToMatch(paths)
                .build();
    }
}

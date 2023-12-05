package com.example.gasip.global.swagger;

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

    /**
     * 전체 API
     */
    @Bean
    public GroupedOpenApi gasipOpenApi() {
        String[] paths = {"/**"};

        return GroupedOpenApi.builder()
                .group("Gasip 서비스 V1")
                .pathsToMatch(paths)
                .build();
    }

    /**
     * 교수 정보 그룹
     */
    @Bean
    public GroupedOpenApi professorOpenApi() {
        String[] paths = {"/all-professors/**"};

        return GroupedOpenApi.builder()
                .group("professors")
                .pathsToMatch(paths)
                .build();
    }

    /**
     * 교수 상세정보 그룹
     */
    @Bean
    public GroupedOpenApi professorDetailsOpenApi() {
        String[] paths = {"/professors/**"};

        return GroupedOpenApi.builder()
                .group("professorDetails")
                .pathsToMatch(paths)
                .build();
    }

    /**
     * 게시글 CRUD 그룹
     */
    @Bean
    public GroupedOpenApi boardOpenApi() {
        String[] paths = {"/boards/**"};

        return GroupedOpenApi.builder()
                .group("boards")
                .pathsToMatch(paths)
                .build();
    }

    /**
     * 게시글 상세정보 그룹
     */
    @Bean
    public GroupedOpenApi boardDetailsOpenApi() {
        String[] paths = {"/details/**"};

        return GroupedOpenApi.builder()
                .group("boardDetails")
                .pathsToMatch(paths)
                .build();
    }
}

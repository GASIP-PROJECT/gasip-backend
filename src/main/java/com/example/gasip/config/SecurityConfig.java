package com.example.gasip.config;

import com.example.gasip.global.security.JwtAuthenticationFilter;
import com.example.gasip.global.security.MemberDetailsService;
import com.example.gasip.global.security.UnauthorizedHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final MemberDetailsService memberDetailsService;
    private final UnauthorizedHandler unauthorizedHandler;

    /**
     * Swagger 접근 관련 URL 제외 코드 추가
     */
    @Bean
    public SecurityFilterChain applicationSecurity(HttpSecurity http) throws Exception {

        http.cors(corsConfigurer -> corsConfigurer.configurationSource(corsConfigurationSource()))
            .csrf(AbstractHttpConfigurer::disable)
            .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(
                SessionCreationPolicy.STATELESS))
            .formLogin(AbstractHttpConfigurer::disable)
            .authenticationProvider(authenticationProvider())
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
            .exceptionHandling(
                exceptionHandling -> exceptionHandling
                    .authenticationEntryPoint(unauthorizedHandler)
            )
            .securityMatcher("/**")
            .authorizeHttpRequests(
                registry -> registry
                    .requestMatchers("/",
                            "/swagger/**",
                            "/swagger-ui/**",
                            "/api-docs/swagger-config",
                            "/api-docs/**",
                            "/configuration/**",
                            "/swagger-ui.html",
                            "/swagger-ui.html/index.html",
                            "/swagger-resources/**",
                            "/webjars/**",
                            "/v3/api-docs/**").permitAll()
                    .requestMatchers(HttpMethod.POST, "/members/signup").permitAll()
                    .requestMatchers(HttpMethod.POST, "/members/login").permitAll()
                    .requestMatchers(HttpMethod.GET, "/all-professors/**").permitAll()
                    .requestMatchers(HttpMethod.GET, "/boards").permitAll()
                    .requestMatchers(HttpMethod.GET, "/boards/**").permitAll()
                    .requestMatchers(HttpMethod.GET, "/details/**").permitAll()
                    .requestMatchers(HttpMethod.GET, "/comments/**").permitAll()
                    .requestMatchers(HttpMethod.GET, "/all-colleges/**").permitAll()
                    .anyRequest().authenticated()
            );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(memberDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(
            List.of("http://localhost:8081"));
        configuration.setAllowedMethods(
            Arrays.asList("HEAD", "POST", "GET", "DELETE", "PUT", "OPTIONS", "PATCH"));
        configuration.addAllowedHeader(("*"));
        configuration.addExposedHeader("Authorization");
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}

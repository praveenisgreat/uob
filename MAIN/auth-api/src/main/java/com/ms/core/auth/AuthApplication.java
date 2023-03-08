package com.ms.core.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Spring boot app.
 *
 * @author praveen
 */
@SpringBootApplication
@EnableJpaAuditing
public class AuthApplication {

    public static void main(String[] args) {

        SpringApplication.run(AuthApplication.class, args);
    }
    
    /*
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*");
                registry.addMapping("/auth/login").allowedOrigins("http://localhost:8081");
            }
        };
    }*/
}


package com.ms.core.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Spring boot app.
 *
 * @author praveen
 */
@SpringBootApplication
@EnableZuulProxy
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
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

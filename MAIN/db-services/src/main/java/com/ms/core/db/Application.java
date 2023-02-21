package com.ms.core.db;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Spring boot app.
 *
 * @author praveen
 */
@SpringBootApplication
@EnableJpaAuditing
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.ms.core.db.model"})  // force scan JPA entities
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }
}

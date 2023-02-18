package com.ms.core.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

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
}

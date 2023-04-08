package com.cokroktoupadek.beersandmealsbackend;

import com.cokroktoupadek.beersandmealsbackend.client.config.JWTKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
@EnableConfigurationProperties(JWTKeyProperties.class)
public class BeerApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(BeerApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(BeerApplication.class);
    }
}

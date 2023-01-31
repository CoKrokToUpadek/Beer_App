package com.cokroktoupadek.beersandmealsbackend;

import com.cokroktoupadek.beersandmealsbackend.client.config.JWTKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;



@SpringBootApplication
@EnableConfigurationProperties(JWTKeyProperties.class)
public class BeerApApplication {
    public static void main(String[] args) {
        SpringApplication.run(BeerApApplication.class, args);
    }

}

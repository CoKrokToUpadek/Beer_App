package com.cokroktoupadek.beersandmealsbackend.client.config;


import com.cokroktoupadek.beersandmealsbackend.mapper.BeerMapperConfigurator;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;


@EnableScheduling
@Configuration
public class BeanConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ModelMapper modelMapper(){
        BeerMapperConfigurator singleton=new BeerMapperConfigurator();
        return singleton.configuration();
    }

}

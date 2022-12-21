package com.cokroktoupadek.beersandmealsapp.client.config;


import com.cokroktoupadek.beersandmealsapp.mapper.BeerMapperConfigurator;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CoreConfig {
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

package com.cokroktoupadek.beer_ap.client.config;


import com.cokroktoupadek.beer_ap.mapper.BeerMapperSingleton;
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
        BeerMapperSingleton singleton=new BeerMapperSingleton();
        return singleton.configuration();
    }
}

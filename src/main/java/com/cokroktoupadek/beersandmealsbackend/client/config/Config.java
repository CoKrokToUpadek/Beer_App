package com.cokroktoupadek.beersandmealsbackend.client.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class Config {

    @Value("${admin.mail.email_address}")
    private String adminMail;

    @Value("${beer.api.base_endpoint}")
    private String beerAppBasicEndpoint;

    @Value("${beer.api.beers}")
    private String allBeers;

    @Value("${meals.api.base_endpoint}")
    private String mealAppBasicEndpoint;

    @Value("${meals.api.search_endpoint}")
    private String mealAppSearchEndpoint;

    @Value("${meals.api.id_search_endpoint}")
    private String mealAppSearchIdEndpoint;




}

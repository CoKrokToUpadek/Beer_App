package com.cokroktoupadek.beer_ap.client.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class BeersAndMealsConfig {

    @Value("${beer.api.base_endpoint}")
    private String beerAppBasicEndpoint;

    @Value("${beer.api.beers}")
    private String allBeers;

    @Value("${meals.api.base_endpoint}")
    private String mealAppBasicEndpoint;

    @Value("${meals.api.search_endpoint}")
    private String mealAppSearchEndpoint;




}

package com.cokroktoupadek.beersandmealsbackend.client;


import com.cokroktoupadek.beersandmealsbackend.client.config.Config;
import com.cokroktoupadek.beersandmealsbackend.domain.dto.beer.BeerDto;
import com.cokroktoupadek.beersandmealsbackend.domain.dto.meals.api_request.MealsApiDto;
import com.cokroktoupadek.beersandmealsbackend.domain.dto.meals.api_request.SingleMealApiDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;

@RequiredArgsConstructor
@Component
public class BeersAndMealsClient {

    private final Config beerConfig;

    private final RestTemplate restTemplate;


    public List<BeerDto> getBeerDto(int beerNo) {
        URI url = buildUriForSingleBeer(beerNo);
        BeerDto[] singleBeer = restTemplate.getForObject(url, BeerDto[].class);
        return Optional.of(singleBeer).map(Arrays::asList)
                .orElse(Collections.emptyList());
    }

    public List<BeerDto> getBeerDtoList() {
        URI url = buildUriForAllBeers();
        BeerDto[] beersList = restTemplate.getForObject(url, BeerDto[].class);
        return Optional.of(beersList).map(Arrays::asList)
                .orElse(Collections.emptyList());
    }

    //api does not have GET request for entire db, so I need to do it this way
    public List<SingleMealApiDto> getMealsDtoList() {
        List<SingleMealApiDto> outputMeals = new ArrayList<>();
        for (char alphabet = 'a'; alphabet <= 'z'; alphabet++) {
            URI url = buildForSingleLetterMeals(alphabet);
            MealsApiDto meals = restTemplate.getForObject(url, MealsApiDto.class);
            if (meals != null) {
                try {
                    outputMeals.addAll(meals.getSingleMealDtoList());
                } catch (NullPointerException e) {
                    /* json have null list for some chars, and I have no idea how to check for meals.getSingleMealDtoList()
                     *since invoking getSingleMealDtoList() for null checking throws null*/
                }
            }

        }
        return Optional.of(outputMeals).orElse(Collections.emptyList());
    }

    public SingleMealApiDto getSingleMealDtoById(Long id) {
        URI url = buildForSingleIdMeals(id);
        MealsApiDto meals = restTemplate.getForObject(url, MealsApiDto.class);
        try {
            return meals.getSingleMealDtoList().get(0);
        }catch (NullPointerException e){
            return null;
        }
    }

    private URI buildUriForAllBeers() {
        return UriComponentsBuilder.fromHttpUrl(beerConfig.getBeerAppBasicEndpoint())
                .pathSegment(beerConfig.getAllBeers())
                .build()
                .encode()
                .toUri();
    }

    private URI buildUriForSingleBeer(int beerNo) {
        return UriComponentsBuilder.fromHttpUrl(beerConfig.getBeerAppBasicEndpoint())
                .pathSegment(beerConfig.getAllBeers())
                .pathSegment(String.valueOf(beerNo))
                .build()
                .encode()
                .toUri();
    }

    private URI buildForSingleLetterMeals(Character letter) {
        return UriComponentsBuilder.fromHttpUrl(beerConfig.getMealAppBasicEndpoint() + beerConfig.getMealAppSearchEndpoint() + letter)
                .build()
                .encode()
                .toUri();
    }

    private URI buildForSingleIdMeals(Long Id) {
        return UriComponentsBuilder.fromHttpUrl(beerConfig.getMealAppBasicEndpoint() + beerConfig.getMealAppSearchIdEndpoint() + Id)
                .build()
                .encode()
                .toUri();
    }

}

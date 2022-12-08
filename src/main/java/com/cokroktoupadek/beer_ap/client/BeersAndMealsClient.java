package com.cokroktoupadek.beer_ap.client;


import com.cokroktoupadek.beer_ap.client.config.BeersAndMealsConfig;
import com.cokroktoupadek.beer_ap.domain.dto.beer.BeerDto;
import com.cokroktoupadek.beer_ap.domain.dto.meals.MealsInputMainObjectDto;
import com.cokroktoupadek.beer_ap.domain.dto.meals.SingleMealDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;

@RequiredArgsConstructor
@Component
public class BeersAndMealsClient {

    private final BeersAndMealsConfig beerConfig;

    private final RestTemplate restTemplate;

    public List<BeerDto> getBeerDto(int beerNo)  {
        URI url=buildUriForSingleBeer(beerNo);
        BeerDto[] singleBeer = restTemplate.getForObject(url,BeerDto[].class);
        return Optional.of(singleBeer).map(Arrays::asList)
                .orElse(Collections.emptyList());
    }

    public List<BeerDto> getBeerDtoList()  {
        URI url=buildUriForAllBeers();
        BeerDto[] beersList = restTemplate.getForObject(url,BeerDto[].class);
        return Optional.of(beersList).map(Arrays::asList)
                .orElse(Collections.emptyList());
    }
    //api does not have get-all endpoint, so I need to do it this way
    public List<SingleMealDto> getMealsDtoList(){
        List<SingleMealDto> outputMeals=new ArrayList<>();
        for (char alphabet = 'a'; alphabet <= 'z'; alphabet++) {
            URI url=buildForSingleMeal(alphabet);
            MealsInputMainObjectDto meals=restTemplate.getForObject(url, MealsInputMainObjectDto.class);
            if (meals != null){
                try {
                    outputMeals.addAll(meals.getSingleMealDtoList());
                }catch (NullPointerException e){
                   /* list returns null for some letters, and I have no idea how to check for meals.getSingleMealDtoList()
                   *since invoking getSingleMealDtoList() for null checking throws null*/
                }
            }

        }
        return Optional.of(outputMeals).orElse(Collections.emptyList());
    }


    private URI buildUriForAllBeers(){
        return UriComponentsBuilder.fromHttpUrl(beerConfig.getBeerAppBasicEndpoint())
                .pathSegment(beerConfig.getAllBeers())
                .build()
                .encode()
                .toUri();
    }

    private URI buildUriForSingleBeer(int beerNo){
        return UriComponentsBuilder.fromHttpUrl(beerConfig.getBeerAppBasicEndpoint())
                .pathSegment(beerConfig.getAllBeers())
                .pathSegment(String.valueOf(beerNo))
                .build()
                .encode()
                .toUri();
    }

    private URI buildForSingleMeal(Character letter){
            return UriComponentsBuilder.fromHttpUrl(beerConfig.getMealAppBasicEndpoint()+beerConfig.getMealAppSearchEndpoint()+letter)
                    .build()
                    .encode()
                    .toUri();
    }


}

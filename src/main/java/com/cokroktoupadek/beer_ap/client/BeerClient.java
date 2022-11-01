package com.cokroktoupadek.beer_ap.client;


import com.cokroktoupadek.beer_ap.client.config.BeerConfig;
import com.cokroktoupadek.beer_ap.domain.dto.beer.BeerDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.*;

@RequiredArgsConstructor
@Component
public class BeerClient {

    private final BeerConfig beerConfig;

    private final RestTemplate restTemplate;



    public List<BeerDto> getBeerDto(int beerNo)  {
        URI url=buildUriForSingleBeer(beerNo);
        BeerDto singleBeer[]= restTemplate.getForObject(url,BeerDto[].class);
        return Optional.of(singleBeer).map(Arrays::asList)
                .orElse(Collections.emptyList());
    }

    public List<BeerDto> getBeerDtoList()  {
        URI url=buildUriForAllBeers();
        BeerDto singleBeer[]= restTemplate.getForObject(url,BeerDto[].class);
        return Optional.of(singleBeer).map(Arrays::asList)
                .orElse(Collections.emptyList());
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


}

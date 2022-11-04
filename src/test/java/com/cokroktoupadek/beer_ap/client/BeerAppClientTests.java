package com.cokroktoupadek.beer_ap.client;


import com.cokroktoupadek.beer_ap.domain.dto.beer.BeerDto;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.IOException;
import java.util.List;


@SpringBootTest
public class BeerAppClientTests {

    @Autowired
    BeerClient beerClient;

    @Test
    void singleBeerFetchTest()  {
        //given
        //when
        List<BeerDto> beerDto= beerClient.getBeerDto(3);
        //then
        System.out.println(beerDto.get(0).toString());
        Assertions.assertEquals(1, beerDto.size());
    }

    @Test
    void multipleBeersFetchTest()  {
        //given
        //when
        List<BeerDto> beerDto= beerClient.getBeerDtoList();
        //then
        for (BeerDto dto : beerDto) {
            System.out.println(dto.toString());
        }
        Assertions.assertTrue(beerDto.size()>10);
    }

}

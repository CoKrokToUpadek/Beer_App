package com.cokroktoupadek.beer_ap.h2_test_db.client;


import com.cokroktoupadek.beer_ap.client.BeerClient;
import com.cokroktoupadek.beer_ap.domain.dto.beer.BeerDto;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.TestPropertySource;

import java.io.IOException;
import java.util.List;


@SpringBootTest
@TestPropertySource("classpath:application-H2TestDb.properties")
public class BeerAppClientTests {

    @Autowired
    BeerClient beerClient;

    @Test
    void singleBeerFetchTest()  {
        //given
        //when
        List<BeerDto> beerDto= beerClient.getBeerDto(17);
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

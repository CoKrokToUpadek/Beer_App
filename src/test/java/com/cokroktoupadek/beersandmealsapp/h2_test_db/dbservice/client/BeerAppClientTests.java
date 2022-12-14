package com.cokroktoupadek.beersandmealsapp.h2_test_db.dbservice.client;


import com.cokroktoupadek.beersandmealsapp.client.BeersAndMealsClient;
import com.cokroktoupadek.beersandmealsapp.domain.dto.beer.BeerDto;
import com.cokroktoupadek.beersandmealsapp.domain.dto.meals.api_request.SingleMealApiDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;


@SpringBootTest
@TestPropertySource("classpath:application-H2TestDb.properties")
public class BeerAppClientTests {

    @Autowired
    BeersAndMealsClient beersAndMealsClient;

    @Test
    void singleBeerFetchTest()  {
        //given
        //when
        List<BeerDto> beerDto= beersAndMealsClient.getBeerDto(17);
        //then
        Assertions.assertEquals(1, beerDto.size());
        System.out.println(beerDto.get(0).toString());
    }

    @Test
    void multipleBeersFetchTest()  {
        //given
        //when
        List<BeerDto> beerDtoList = beersAndMealsClient.getBeerDtoList();
        //then
        Assertions.assertTrue(beerDtoList.size()>10);
        for (BeerDto dto : beerDtoList) {
            System.out.println(dto.toString());
        }
    }

    @Test
    void multipleMealsFetchTest()  {
        //given
        //when
        List<SingleMealApiDto> mealsInputDtoList= beersAndMealsClient.getMealsDtoList();
        //then
        for (SingleMealApiDto dto : mealsInputDtoList) {
            System.out.println(dto.toString());
        }
        Assertions.assertTrue(mealsInputDtoList.size()>5);
    }

}

package com.cokroktoupadek.beer_ap.h2_test_db.client;


import com.cokroktoupadek.beer_ap.client.BeersAndMealsClient;
import com.cokroktoupadek.beer_ap.domain.dto.beer.BeerDto;
import com.cokroktoupadek.beer_ap.domain.dto.meals.MealsInputMainObjectDto;
import com.cokroktoupadek.beer_ap.domain.dto.meals.SingleMealDto;
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
        List<SingleMealDto> mealsInputDtoList= beersAndMealsClient.getMealsDtoList();
        //then
        for (SingleMealDto dto : mealsInputDtoList) {
            System.out.println(dto.toString());
        }
        Assertions.assertTrue(mealsInputDtoList.size()>5);
    }

}

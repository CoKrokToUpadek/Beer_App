package com.cokroktoupadek.beersandmealsbackend.h2_test_db.controller;


import com.cokroktoupadek.beersandmealsbackend.controller.UserController;
import com.cokroktoupadek.beersandmealsbackend.domain.dto.beer.BeerDto;
import com.cokroktoupadek.beersandmealsbackend.domain.dto.meals.program.MealDto;
import com.cokroktoupadek.beersandmealsbackend.domain.dto.user.CreatedUserDto;
import com.cokroktoupadek.beersandmealsbackend.facade.UserFacade;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
@TestPropertySource("classpath:application-H2TestDb.properties")
@WithMockUser(username = "admin", roles = {"ADMIN"})
public class UserControllerTests {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    UserFacade userFacade;
    @Test
    void testCreateUser() throws Exception {
        // Given
        CreatedUserDto dto=new CreatedUserDto();
        Gson gson = new Gson();
        String jsonContent = gson.toJson(dto);
        when(userFacade.createUser(dto)).thenReturn("ok");
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/user/create_user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void testGetBeerList()  throws Exception {
        // Given
        List<BeerDto> beerDtoList=new ArrayList<>();
        beerDtoList.add(new BeerDto());
        when(userFacade.getBeerList()).thenReturn(beerDtoList);
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/user/get_beers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)));
    }

    @Test
    void testGetMealList()  throws Exception {
        // Given
        List<MealDto> mealDtoList=new ArrayList<>();
        mealDtoList.add(new MealDto());
        when(userFacade.getMealList()).thenReturn(mealDtoList);
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/user/get_meals")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)));
    }

    @Test
    void testAddBeerToFavorite()  throws Exception {
        // Given
        when(userFacade.addBeerToFavorite("testBeer","admin")).thenReturn("ok");
        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("beerName", "testBeer");
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/user/add_beer_to_favorite")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                         .params(requestParams))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
    @Test
    void testRemoveBeerFromFavorite()  throws Exception {
        // Given
        when(userFacade.removeBeerFromFavorite("testBeer","admin")).thenReturn("ok");
        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("beerName", "testBeer");
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/user/remove_beer_from_favorite")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .params(requestParams))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void testGetMyFavoriteBeers()  throws Exception {
        // Given
        List<BeerDto> beerDtoList=new ArrayList<>();
        beerDtoList.add(new BeerDto());
        beerDtoList.add(new BeerDto());
        when(userFacade.getBeerFavoriteList("admin")).thenReturn(beerDtoList);
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/user/get_my_favorite_beers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void testAddMealToFavorite()  throws Exception {
        // Given
        when(userFacade.addBeerToFavorite("testMeal","admin")).thenReturn("ok");
        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("mealName", "testMeal");
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/user/add_meal_to_favorite")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .params(requestParams))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
    @Test
    void testRemoveMealFromFavorite()  throws Exception {
        // Given
        when(userFacade.removeMealFromFavorite("testMeal","admin")).thenReturn("ok");
        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("mealName", "testMeal");
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/user/remove_meal_from_favorite")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .params(requestParams))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void testGetMyFavoriteMeals()  throws Exception {
        // Given
        List<MealDto> mealDtoList =new ArrayList<>();
        mealDtoList.add(new MealDto());
        mealDtoList.add(new MealDto());
        when(userFacade.getMealFavoriteList("admin")).thenReturn(mealDtoList);
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/user/get_my_favorite_meals")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

}

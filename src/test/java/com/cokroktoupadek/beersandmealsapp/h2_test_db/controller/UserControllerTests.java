package com.cokroktoupadek.beersandmealsapp.h2_test_db.controller;


import com.cokroktoupadek.beersandmealsapp.controller.UserController;
import com.cokroktoupadek.beersandmealsapp.domain.dto.beer.BeerDto;
import com.cokroktoupadek.beersandmealsapp.domain.dto.meals.program.MealDto;
import com.cokroktoupadek.beersandmealsapp.domain.dto.user.CreatedUserDto;
import com.cokroktoupadek.beersandmealsapp.facade.UserFacade;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@SpringBootTest
@RunWith(SpringRunner.class)
@WithMockUser(username = "admin", roles = {"ADMIN"})
@TestPropertySource("classpath:application-H2TestDb.properties")
public class UserControllerTests {

    MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Mock
    UserFacade userFacade;
    @MockBean
    private UserController userController;

    @Test
    void testCreateUser() throws Exception {
        // Given
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
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
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
        List<BeerDto> beerDtoList=new ArrayList<>();
        beerDtoList.add(new BeerDto());
        when(userFacade.getBeerList()).thenReturn(beerDtoList);
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/user/get_beers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void testGetMealList()  throws Exception {
        // Given
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
        List<MealDto> mealDtoList=new ArrayList<>();
        mealDtoList.add(new MealDto());
        when(userFacade.getMealList()).thenReturn(mealDtoList);
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/user/get_meals")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void testAddBeerToFavorite()  throws Exception {
        // Given
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
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
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
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
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
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
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
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
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
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
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
        when(userFacade.getMealFavoriteList("admin")).thenReturn(mealDtoList);
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/user/get_my_favorite_meals")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

}

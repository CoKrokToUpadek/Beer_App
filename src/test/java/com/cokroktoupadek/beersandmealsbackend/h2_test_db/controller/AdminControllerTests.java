package com.cokroktoupadek.beersandmealsbackend.h2_test_db.controller;

import com.cokroktoupadek.beersandmealsbackend.controller.AdminController;
import com.cokroktoupadek.beersandmealsbackend.domain.dto.user.UserDto;

import com.cokroktoupadek.beersandmealsbackend.facade.AdminFacade;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;


@SpringJUnitWebConfig
@WebMvcTest(AdminController.class)
@AutoConfigureMockMvc(addFilters = false)
@TestPropertySource("classpath:application-H2TestDb.properties")
public class AdminControllerTests {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    AdminFacade adminFacade;

    @Test
    void testEncodePassword() throws Exception {
        // Given
        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("login", "test");
        when(adminFacade.encodePassword("test")).thenReturn("ok");
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/admin/encode_user_password")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .params(requestParams))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }


    @Test
    void testChangeUserStatusByLogin() throws Exception {
        // Given
        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("login", "test");
        requestParams.add("status", "1");
        when(adminFacade.setUserStatus("test", 1)).thenReturn("ok");
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/admin/set_user_status")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .params(requestParams))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void testSetUserRoleByLogin() throws Exception {
        // Given
        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("login", "test");
        requestParams.add("role", "user");
        when(adminFacade.setUserRole("test", "user")).thenReturn("ok");
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/admin/set_user_role")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .params(requestParams))
                .andExpect(MockMvcResultMatchers.status().is(200));

    }

    @Test
    void testGetUsersList() throws Exception {
        // Given
        List<UserDto> userDtoList = new ArrayList<>();
        UserDto userDto = new UserDto();
        userDto.setFirstName("testName");
        userDtoList.add(userDto);
        userDtoList.add(userDto);

        when(adminFacade.getUsers()).thenReturn(userDtoList);
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/admin/get_user_list")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));
    }

    @Test
    void testUpdateLocalMealsDb() throws Exception {
        // Given
        when(adminFacade.updateMealDbFacade()).thenReturn("ok");
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/admin/update_meals_db")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void testDeleteMealFromDb() throws Exception {
        // Given
        when(adminFacade.deleteSingleMeal("test")).thenReturn("ok");
        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("mealName", "test");
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/admin/delete_meal")
                        .contentType(MediaType.APPLICATION_JSON)
                        .params(requestParams))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void testDeleteAllMealsFromDb() throws Exception {
        // Given
        when(adminFacade.deleteAllBeers()).thenReturn("ok");
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/admin/delete_all_meals")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void testUpdateLocalBeersDb() throws Exception {
        // Given
        when(adminFacade.updateBeerDbFacade()).thenReturn("ok");
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/admin/update_beer_db")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void testDeleteBeerFromDb() throws Exception {
        // Given
        when(adminFacade.deleteSingleBeer("test")).thenReturn("ok");
        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("beerName", "test");
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/admin/delete_beer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .params(requestParams))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void testDeleteAllBeersFromDb() throws Exception {
        // Given
        when(adminFacade.deleteAllBeers()).thenReturn("ok");
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/admin/delete_all_beers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

}

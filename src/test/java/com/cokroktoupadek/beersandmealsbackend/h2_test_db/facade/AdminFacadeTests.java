package com.cokroktoupadek.beersandmealsbackend.h2_test_db.facade;


import com.cokroktoupadek.beersandmealsbackend.client.BeersAndMealsClient;
import com.cokroktoupadek.beersandmealsbackend.domain.dto.beer.BeerDto;
import com.cokroktoupadek.beersandmealsbackend.domain.entity.beer.BeerEntity;
import com.cokroktoupadek.beersandmealsbackend.domain.entity.meal.MealEntity;
import com.cokroktoupadek.beersandmealsbackend.domain.entity.user.UserEntity;
import com.cokroktoupadek.beersandmealsbackend.errorhandlers.BeerNotFoundException;
import com.cokroktoupadek.beersandmealsbackend.facade.AdminFacade;
import com.cokroktoupadek.beersandmealsbackend.mapper.BeerEntityFilterAndSaver;
import com.cokroktoupadek.beersandmealsbackend.mapper.Mapper;
import com.cokroktoupadek.beersandmealsbackend.service.beer.BeerAndMealEntityManipulatorDbService;
import com.cokroktoupadek.beersandmealsbackend.service.beer.BeerDbService;
import com.cokroktoupadek.beersandmealsbackend.service.meal.MealDbService;
import com.cokroktoupadek.beersandmealsbackend.service.user.UserDbService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class AdminFacadeTests {
    BeerDbService beerDbService;
    Mapper mapper;
    BeersAndMealsClient beersAndMealsClient;
    BeerEntityFilterAndSaver beerEntityFilter;
    BeerAndMealEntityManipulatorDbService beerAndMealEntityManipulatorDbService;
    MealDbService mealDbService;
    UserDbService userDbService;
    PasswordEncoder encoder;
    AdminFacade adminFacade;

    @BeforeEach
    void setup() {
        beerDbService = mock(BeerDbService.class);
        mapper = mock(Mapper.class);
        beersAndMealsClient = mock(BeersAndMealsClient.class);
        beerEntityFilter = mock(BeerEntityFilterAndSaver.class);
        beerAndMealEntityManipulatorDbService = mock(BeerAndMealEntityManipulatorDbService.class);
        userDbService = mock(UserDbService.class);
        encoder = mock(PasswordEncoder.class);
        mealDbService=mock(MealDbService.class);
        adminFacade = new AdminFacade(beerDbService, mapper, beersAndMealsClient,
                beerEntityFilter, beerAndMealEntityManipulatorDbService,
                mealDbService, userDbService);


    }

////////////////////////////////////meals/////////////////////////////////////////

    @Test
    void testUpdateMealDbFacade(){
        //given
        //when
        String output = adminFacade.updateMealDbFacade();
        //then
        Assertions.assertEquals("meal list updated successfully",output);
    }
    @Test
    void updateSingleMealFacade() {
        //given
        doNothing().when(mealDbService).mealDuplicateVerifyAndSave(any());
        //when
        String output=adminFacade.updateSingleMealFacade(1L);
        Assertions.assertEquals("meal updated successfully",output);
    }
    @Test
    void testDeleteSingleMeal() {
        //given
        doNothing().when(beerAndMealEntityManipulatorDbService).mealEntityDeleter(any());
        //when
        String output=adminFacade.deleteSingleMeal("test");
        //then
        Assertions.assertEquals("meal deleted successfully",output);
    }
    @Test
     void deleteAllMealsEmptyDb(){
        //given
        when(mealDbService.findAll()).thenReturn(new ArrayList<>());
        //when
        String output=adminFacade.deleteAllMeals();
        //then
        Assertions.assertEquals("meal db is empty",output);
    }


    @Test
    void deleteAllMealsFullDb(){
        //given
        when(mealDbService.findAll()).thenReturn(List.of(new MealEntity()));
        //when
        String output=adminFacade.deleteAllMeals();
        //then
        Assertions.assertEquals("all meals has been deleted successfully",output);
    }


    ////////////////////////////////////beers/////////////////////////////////////////

    @Test
    void testUpdateBeerDbFacade(){
        //given
        //when
        String output = adminFacade.updateBeerDbFacade();
        //then
        Assertions.assertEquals("beer list updated successfully", output);
    }
    @Test
    void updateSingleBeerFacade(){
        //given
        when(beersAndMealsClient.getBeerDto(0)).thenReturn(List.of(new BeerDto()));
        when(mapper.mapToBeerEntity(any())).thenReturn(new BeerEntity());
        //when
        String output = adminFacade.updateSingleBeerFacade(0);
        //then
        Assertions.assertEquals("beer updated successfully", output);
    }
    @Test
    void deleteSingleBeerNotFound(){
        //given
        //when
        Exception exception = assertThrows(BeerNotFoundException.class, () -> {
            adminFacade.deleteSingleBeer("test");
        });
        //then
        Assertions.assertEquals(BeerNotFoundException.class,exception.getClass());
    }

    @Test
    void deleteSingleBeerFound(){
        //given
        when(beerDbService.findByName("test")).thenReturn(Optional.of(new BeerEntity()));
        //when
        String output=adminFacade.deleteSingleBeer("test");
        //then
        Assertions.assertEquals("beer was deleted successfully", output);
    }


    @Test
    void testDeleteAllBeersDbEmpty() {
        //given
        when(beerDbService.findAll()).thenReturn(new ArrayList<>());
        //when
        String output = adminFacade.deleteAllBeers();
        //then
        Assertions.assertEquals("db is empty", output);
    }

    @Test
    void testDeleteAllBeersDbWithData() {
        //given
        when(beerDbService.findAll()).thenReturn(List.of(new BeerEntity()));
        //when
        String output = adminFacade.deleteAllBeers();
        //then
        Assertions.assertEquals("all beers has been deleted successfully", output);
    }

    ////////////////////////////////////administrative/////////////////////////////////////////
    @Test
    void testSetUserRoleCaseNotFound(){
        //given
        //when
        String output=adminFacade.setUserRole("test","test");
        //then
        Assertions.assertEquals("user with login: test not found.", output);
    }

    @Test
    void testSetUserRoleCaseAdmin(){
        //given
        UserEntity user=new UserEntity();
        user.setLogin("test");
        when(userDbService.findByLogin("test")).thenReturn(Optional.of(user));
        //when
        String output=adminFacade.setUserRole("test","admin");
        //then
        Assertions.assertEquals("User with login: test is now admin", output);
    }

    @Test
    void testSetUserRoleCaseUser(){
        //given
        UserEntity user=new UserEntity();
        user.setLogin("test");
        when(userDbService.findByLogin("test")).thenReturn(Optional.of(user));
        //when
        String output=adminFacade.setUserRole("test","user");
        //then
        Assertions.assertEquals("User with login: test is now user", output);
    }

    @Test
    void testSetUserRoleCaseWrongRole(){
        //given
        UserEntity user=new UserEntity();
        user.setLogin("test");
        when(userDbService.findByLogin("test")).thenReturn(Optional.of(user));
        //when
        String output=adminFacade.setUserRole("test","wrongRole");
        //then
        Assertions.assertEquals("specified role does not exist", output);
    }

    @Test
    void testSetUserStatusCaseNotFound(){
        //given
        //when
        String output=adminFacade.setUserStatus("test",1);
        //then
        Assertions.assertEquals("user with login: test not found.", output);
    }

    @Test
    void testSetUserStatusCaseActive(){
        //given
        UserEntity user=new UserEntity();
        user.setLogin("test");
        when(userDbService.findByLogin("test")).thenReturn(Optional.of(user));
        //when
        String output=adminFacade.setUserStatus("test",1);
        //then
        Assertions.assertEquals("User with login: test is now active", output);
    }


    @Test
    void testSetUserStatusCaseInactive(){
        //given
        UserEntity user=new UserEntity();
        user.setLogin("test");
        when(userDbService.findByLogin("test")).thenReturn(Optional.of(user));
        //when
        String output=adminFacade.setUserStatus("test",0);
        //then
        Assertions.assertEquals("User with login: test is now inactive", output);
    }

    @Test
    void testSetUserStatusCaseWrongRole(){
        //given
        UserEntity user=new UserEntity();
        user.setLogin("test");
        when(userDbService.findByLogin("test")).thenReturn(Optional.of(user));
        //when
        String output=adminFacade.setUserStatus("test",2);
        //then
        Assertions.assertEquals("status can be either 0 for banned, or 1 for active", output);
    }

    @Test
    void testEncodePasswordCaseUserNotFound(){
        //given
        //when
        String output=adminFacade.encodePassword("test");
        //then
        Assertions.assertEquals("user with login: test not found.", output);
    }

    @Test
    void testEncodePasswordCasePasswordNotEncoded(){
        //given
        UserEntity user=new UserEntity();
        user.setLogin("test");
        user.setPassword("password");
        when(userDbService.findByLogin("test")).thenReturn(Optional.of(user));
        //when
        String output=adminFacade.encodePassword("test");
        //then
        Assertions.assertEquals("password for user with login: test has been encoded successfully", output);
    }

    @Test
    void testEncodePasswordCasePasswordAlreadyEncoded(){
        //given
        UserEntity user=new UserEntity();
        user.setLogin("test");
        //random encoded test password from db
        user.setPassword("$2a$10$qjURqRu9tU0NEu61SRHcbez7ep0j.ZztbRxT9JieBG2vxYa59XmOa");
        when(userDbService.findByLogin("test")).thenReturn(Optional.of(user));
        //when
        String output=adminFacade.encodePassword("test");
        //then
        Assertions.assertEquals("user password has already been encoded", output);
    }

}

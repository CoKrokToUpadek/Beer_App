package com.cokroktoupadek.beersandmealsbackend.h2_test_db.facade;


import com.cokroktoupadek.beersandmealsbackend.client.config.TokenService;
import com.cokroktoupadek.beersandmealsbackend.domain.dto.beer.BeerDto;
import com.cokroktoupadek.beersandmealsbackend.domain.dto.meals.program.MealDto;
import com.cokroktoupadek.beersandmealsbackend.domain.dto.user.CreatedUserDto;
import com.cokroktoupadek.beersandmealsbackend.domain.entity.beer.BeerEntity;
import com.cokroktoupadek.beersandmealsbackend.domain.entity.meal.MealEntity;
import com.cokroktoupadek.beersandmealsbackend.domain.entity.user.UserEntity;
import com.cokroktoupadek.beersandmealsbackend.errorhandlers.BeerDbIsEmptyException;
import com.cokroktoupadek.beersandmealsbackend.errorhandlers.MealDbIsEmptyException;
import com.cokroktoupadek.beersandmealsbackend.facade.UserFacade;
import com.cokroktoupadek.beersandmealsbackend.mapper.Mapper;
import com.cokroktoupadek.beersandmealsbackend.service.beer.BeerDbService;
import com.cokroktoupadek.beersandmealsbackend.service.meal.MealDbService;
import com.cokroktoupadek.beersandmealsbackend.service.user.UserDbService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class UserFacadeTests {

    UserDbService userDbService;
    BeerDbService beerDbService;
    MealDbService mealDbService;
    Mapper mapper;
    PasswordEncoder passwordEncoder;
    UserFacade userFacade;

    TokenService tokenService;



    @BeforeEach
    void setup() {
        userDbService=mock(UserDbService.class);
        beerDbService=mock(BeerDbService.class);
        mealDbService=mock(MealDbService.class);
        mapper=mock(Mapper.class);
        passwordEncoder=mock(PasswordEncoder.class);
        userFacade=new UserFacade(userDbService, beerDbService,  mapper,mealDbService,tokenService);
    }

    @Test
    void testCreateUserSuccess(){
        //given
        CreatedUserDto userDto=new CreatedUserDto("test","test","test","test","test","test");
        when(mapper.mapNewUserEntity(userDto)).thenReturn(new UserEntity("test","test","test","test","test","test", "test",LocalDate.now()));
        //when
        String output= userFacade.createUser(userDto);
        //then
        Assertions.assertEquals("user was created successfully",output);
    }

    @Test
    void testCreateUserLoginTaken(){
        //given
        UserEntity userEntity=new UserEntity("test","test","test","test","test","test", "test",LocalDate.now());
        CreatedUserDto userDto=new CreatedUserDto("test","test","test","test","test","test");
        when(userDbService.findByLogin(userDto.getLogin())).thenReturn(Optional.of(userEntity));
        //when
        String output= userFacade.createUser(userDto);
        //then
        Assertions.assertEquals("Login was already taken.",output);
    }

    @Test
    void testCreateUserEmailTaken(){
        //given
        UserEntity userEntity=new UserEntity("test","test","test","test","test","test", "test",LocalDate.now());
        CreatedUserDto userDto=new CreatedUserDto("test","test","test","test","test","test");
        when(userDbService.findByLogin(userDto.getLogin())).thenReturn(Optional.empty());
        when(userDbService.findByEmail(userDto.getEmail())).thenReturn(Optional.of(userEntity));
        //when
        String output= userFacade.createUser(userDto);
        //then
        Assertions.assertEquals("Account with specified email already exist.",output);
    }

    @Test
    void testCreateUserMissingInformation(){
        //given
        CreatedUserDto userDto=new CreatedUserDto(null,null,"test","test",null,"test");
        when(userDbService.findByLogin(userDto.getLogin())).thenReturn(Optional.empty());
        when(userDbService.findByEmail(userDto.getEmail())).thenReturn(Optional.empty());
        //when
        String output= userFacade.createUser(userDto);
        //then
        Assertions.assertEquals("Information provided in form was incomplete or invalid.",output);
    }

    @Test
    void testCreateUserMissingInformationButValid(){
        //given
        CreatedUserDto userDto=new CreatedUserDto(null,null,"test","test","test","test");
        when(userDbService.findByLogin(userDto.getLogin())).thenReturn(Optional.empty());
        when(userDbService.findByEmail(userDto.getEmail())).thenReturn(Optional.empty());
        when(mapper.mapNewUserEntity(userDto)).thenReturn(new UserEntity("test","test","test","test","test","test", "test",LocalDate.now()));
        //when
        String output= userFacade.createUser(userDto);
        //then
        Assertions.assertEquals("user was created successfully",output);
    }



    @Test
    void testGetBeerListThrowsException()  {
        //given
        //when
        Exception exception = assertThrows(BeerDbIsEmptyException.class, () -> {
            userFacade.getBeerList();
        });
        //then
        Assertions.assertEquals("class com.cokroktoupadek.beersandmealsbackend.errorhandlers.BeerDbIsEmptyException",exception.getClass().toString());
    }

    @Test
    void testGetBeerListDoesNotThrow() throws BeerDbIsEmptyException {
        //given
        List<BeerEntity> beers=new ArrayList<>();
        beers.add(new BeerEntity());
        List<BeerDto> beerDtoList=new ArrayList<>();
        beerDtoList.add(new BeerDto());

        when(beerDbService.findAll()).thenReturn(beers);
        when(mapper.mapToBeerDtoList(beers)).thenReturn(beerDtoList);
        //when
        List<BeerDto> output=userFacade.getBeerList();
        //then
        Assertions.assertEquals(1,output.size());
    }

    @Test
    void testGetMealListThrowsException()  {
        //given
        //when
        Exception exception = assertThrows(MealDbIsEmptyException.class, () -> {
            userFacade.getMealList();
        });
        //then
        Assertions.assertEquals("class com.cokroktoupadek.beersandmealsbackend.errorhandlers.MealDbIsEmptyException",exception.getClass().toString());
    }

    @Test
    void testGetMealListDoesNotThrow() throws MealDbIsEmptyException {
        //given
        List<MealEntity> meals=new ArrayList<>();
        meals.add(new MealEntity());
        List<MealDto> mealDtoList=new ArrayList<>();
        mealDtoList.add(new MealDto());
        when(mealDbService.findAll()).thenReturn(meals);
        when(mapper.mapMealEntityToMealDtoList(meals)).thenReturn(mealDtoList);
        //when
        List<MealDto> output=userFacade.getMealList();
        //then
        Assertions.assertEquals(1,output.size());
    }
    @Test
    void testGetBeerFavoriteList(){
        //given
        UserEntity userEntity=new UserEntity("test","test","test","test","test","test", "test",LocalDate.now());
        userEntity.setFavouredBeers(new ArrayList<>());
        userEntity.getFavouredBeers().add(new BeerEntity());
        List<BeerDto> beerDtoList=new ArrayList<>();
        beerDtoList.add(new BeerDto());
        when(userDbService.findByLogin("test")).thenReturn(Optional.of(userEntity));
        when(mapper.mapToBeerDtoList(userEntity.getFavouredBeers())).thenReturn(beerDtoList);
        //when
        List<BeerDto> output=userFacade.getBeerFavoriteList("test");
        //then
        Assertions.assertEquals(1,output.size());
    }
    @Test
    void testGetMealFavoriteList(){
        //given
        UserEntity userEntity=new UserEntity("test","test","test","test","test","test", "test",LocalDate.now());
        userEntity.setFavouredMeals(new ArrayList<>());
        userEntity.getFavouredMeals().add(new MealEntity());
        List<MealDto> mealDtoList=new ArrayList<>();
        mealDtoList.add(new MealDto());
        when(userDbService.findByLogin("test")).thenReturn(Optional.of(userEntity));
        when(mapper.mapMealEntityToMealDtoList(userEntity.getFavouredMeals())).thenReturn(mealDtoList);
        //when
        List<MealDto> output=userFacade.getMealFavoriteList("test");
        //then
        Assertions.assertEquals(1,output.size());
    }

    @Test
    void testAddBeerToFavoriteBeerNotFound(){
        //given
        //when
        String output=userFacade.addBeerToFavorite("testbeer","testlogin");
        //then
        Assertions.assertEquals("beer not found",output);
    }

    @Test
    void testAddBeerToFavoriteBeerAlreadyOnYourList(){
        //given
        UserEntity userEntity=new UserEntity("test","test","test","test","test","test", "test",LocalDate.now());
        userEntity.setFavouredBeers(new ArrayList<>());
        BeerEntity entity=new BeerEntity();
        entity.setName("testbeer");
        userEntity.getFavouredBeers().add(entity);
        when(userDbService.findByLogin("testlogin")).thenReturn(Optional.of(userEntity));
        when(beerDbService.findByName("testbeer")).thenReturn(Optional.of(entity));
        //when
        String output=userFacade.addBeerToFavorite("testbeer","testlogin");
        //then
        Assertions.assertEquals("beer is already on your list",output);
    }

    @Test
    void testAddBeerToFavoriteSuccess(){
        //given
        UserEntity userEntity=new UserEntity("test","test","test","test","test","test", "test",LocalDate.now());
        userEntity.setFavouredBeers(new ArrayList<>());
        BeerEntity entity=new BeerEntity();
        entity.setName("testbeer");
        when(userDbService.findByLogin("testlogin")).thenReturn(Optional.of(userEntity));
        when(beerDbService.findByName("testbeer")).thenReturn(Optional.of(entity));
        //when
        String output=userFacade.addBeerToFavorite("testbeer","testlogin");
        //then
        Assertions.assertEquals("beer testbeer has been added to your favorite list",output);
    }
    ///////////////////////////////////////////////
    @Test
    void testAddMealToFavoriteMealNotFound(){
        //given
        //when
        String output=userFacade.addMealToFavorite("testmeal","testlogin");
        //then
        Assertions.assertEquals("meal not found",output);
    }

    @Test
    void testAddMealToFavoriteMealAlreadyOnYourList(){
        //given
        UserEntity userEntity=new UserEntity("test","test","test","test","test","test", "test",LocalDate.now());
        userEntity.setFavouredMeals(new ArrayList<>());
        MealEntity entity=new MealEntity();
        entity.setName("testMeal");
        userEntity.getFavouredMeals().add(entity);
        when(userDbService.findByLogin("testlogin")).thenReturn(Optional.of(userEntity));
        when(mealDbService.findByName("testMeal")).thenReturn(Optional.of(entity));
        //when
        String output=userFacade.addMealToFavorite("testMeal","testlogin");
        //then
        Assertions.assertEquals("meal is already on your list",output);
    }

    @Test
    void testAddMealToFavoriteSuccess(){
        //given
        UserEntity userEntity=new UserEntity("test","test","test","test","test","test", "test",LocalDate.now());
        userEntity.setFavouredMeals(new ArrayList<>());
        MealEntity entity=new MealEntity();
        entity.setName("testMeal");
        when(userDbService.findByLogin("testlogin")).thenReturn(Optional.of(userEntity));
        when(mealDbService.findByName("testMeal")).thenReturn(Optional.of(entity));
        //when
        String output=userFacade.addMealToFavorite("testMeal","testlogin");
        //then
        Assertions.assertEquals("meal testMeal has been added to your favorite list",output);
    }
    ///////////////////////////////////////////
    @Test
    void testRemoveBeerFromFavoriteNotFound(){
        //given
        //when
        String output=userFacade.removeBeerFromFavorite("testbeer","testlogin");
        //then
        Assertions.assertEquals("Beer not found in db. Check for typos",output);
    }

        @Test
    void testRemoveBeerFromFavoriteNotFoundOnYourList(){
        //given
        UserEntity userEntity=new UserEntity("test","test","test","test","test","test", "test",LocalDate.now());
        userEntity.setFavouredBeers(new ArrayList<>());
        BeerEntity entity=new BeerEntity();
        entity.setName("testbeer");
        when(userDbService.findByLogin("testlogin")).thenReturn(Optional.of(userEntity));
        when(beerDbService.findByName("testbeer")).thenReturn(Optional.of(entity));
        //when
        String output=userFacade.removeBeerFromFavorite("testbeer","testlogin");
        //then
        Assertions.assertEquals("Beer not found on your list",output);
    }

    @Test
    void testRemoveBeerFromFavoriteSuccess(){
        //given
        UserEntity userEntity=new UserEntity("test","test","test","test","test","test", "test",LocalDate.now());
        userEntity.setFavouredBeers(new ArrayList<>());
        BeerEntity entity=new BeerEntity();
        entity.setName("testbeer");
        userEntity.getFavouredBeers().add(entity);
        when(userDbService.findByLogin("testlogin")).thenReturn(Optional.of(userEntity));
        when(beerDbService.findByName("testbeer")).thenReturn(Optional.of(entity));
        //when
        String output=userFacade.removeBeerFromFavorite("testbeer","testlogin");
        //then
        Assertions.assertEquals("beer testbeer has been removed from your favorite list",output);
    }

    ///////////////////////////////////////////
    @Test
    void testRemoveMealFromFavoriteNotFound(){
        //given
        //when
        String output=userFacade.removeMealFromFavorite("testbeer","testlogin");
        //then
        Assertions.assertEquals("Meal not found in db. Check for typos",output);
    }

    @Test
    void testRemoveMealFromFavoriteNotFoundOnYourList(){
        //given
        UserEntity userEntity=new UserEntity("test","test","test","test","test","test", "test",LocalDate.now());
        userEntity.setFavouredMeals(new ArrayList<>());
        MealEntity entity=new MealEntity();
        entity.setName("testMeal");
        when(userDbService.findByLogin("testlogin")).thenReturn(Optional.of(userEntity));
        when(mealDbService.findByName("testMeal")).thenReturn(Optional.of(entity));
        //when
        String output=userFacade.removeMealFromFavorite("testMeal","testlogin");
        //then
        Assertions.assertEquals("Meal not found on your list",output);
    }

    @Test
    void testRemoveMealFromFavoriteSuccess(){
        //given
        UserEntity userEntity=new UserEntity("test","test","test","test","test","test", "test",LocalDate.now());
        userEntity.setFavouredMeals(new ArrayList<>());
        MealEntity entity=new MealEntity();
        entity.setName("testMeal");
        userEntity.getFavouredMeals().add(entity);
        when(userDbService.findByLogin("testlogin")).thenReturn(Optional.of(userEntity));
        when(mealDbService.findByName("testMeal")).thenReturn(Optional.of(entity));
        //when
        String output=userFacade.removeMealFromFavorite("testMeal","testlogin");
        //then
        Assertions.assertEquals("meal testMeal has been removed from your favorite list",output);
    }
}

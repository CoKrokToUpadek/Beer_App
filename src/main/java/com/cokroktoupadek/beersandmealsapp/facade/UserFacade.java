package com.cokroktoupadek.beersandmealsapp.facade;

import com.cokroktoupadek.beersandmealsapp.domain.dto.beer.BeerDto;
import com.cokroktoupadek.beersandmealsapp.domain.dto.meals.program.MealDto;
import com.cokroktoupadek.beersandmealsapp.domain.dto.user.CreatedUserDto;
import com.cokroktoupadek.beersandmealsapp.domain.entity.beer.BeerEntity;
import com.cokroktoupadek.beersandmealsapp.domain.entity.meal.MealEntity;
import com.cokroktoupadek.beersandmealsapp.domain.entity.user.UserEntity;
import com.cokroktoupadek.beersandmealsapp.errorhandlers.BeerDbIsEmptyException;
import com.cokroktoupadek.beersandmealsapp.errorhandlers.UserCreationException;
import com.cokroktoupadek.beersandmealsapp.mapper.Mapper;
import com.cokroktoupadek.beersandmealsapp.service.beer.BeerDbService;
import com.cokroktoupadek.beersandmealsapp.service.meal.MealDbService;
import com.cokroktoupadek.beersandmealsapp.service.user.UserDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class UserFacade {


    private UserDbService userDbService;

    private BeerDbService beerDbService;

    private MealDbService mealDbService;

    private Mapper mapper;
    @Autowired
    public UserFacade(UserDbService userDbService, BeerDbService beerDbService, Mapper mapper,MealDbService mealDbService) {
        this.userDbService = userDbService;
        this.beerDbService = beerDbService;
        this.mapper = mapper;
        this.mealDbService=mealDbService;
    }




    public String createUserFacade(CreatedUserDto userDto) {

        UserEntity entity;
        if(userDbService.findByLogin(userDto.getLogin()).isPresent()) {
            return UserCreationException.ERR_LOGIN_TAKEN;
        }
       if (userDbService.findByEmail(userDto.getEmail()).isPresent()) {
           return UserCreationException.ERR_EMAIL_TAKEN;
        }
       if(Stream.of(userDto.getAddress(), userDto.getEmail(), userDto.getLogin(), userDto.getPassword(),
                userDto.getFirstName(),userDto.getLastName()).anyMatch(Objects::isNull)){
           return UserCreationException.ERR_MISSING_INFORMATION;
        }else {
             entity= mapper.mapNewUserEntity(userDto);
             userDbService.save(entity);
             return "user was created successfully";
        }

    }

    public List<BeerDto> getBeerList() throws BeerDbIsEmptyException {
      List<BeerEntity> beerEntities= beerDbService.findAll();
      if (!beerEntities.isEmpty()){
          return mapper.mapToBeerDtoList(beerEntities);
      }else {
          throw new BeerDbIsEmptyException();
      }
    }

    public List<MealDto> getMealList() throws BeerDbIsEmptyException {
        List<MealEntity> mealEntityList= mealDbService.findAll();
        if (!mealEntityList.isEmpty()){
            return mapper.mapMealEntityToMealDtoList(mealEntityList);
        }else {
            throw new BeerDbIsEmptyException();
        }
    }


}

package com.cokroktoupadek.beersandmealsbackend.facade;

import com.cokroktoupadek.beersandmealsbackend.domain.dto.beer.BeerDto;
import com.cokroktoupadek.beersandmealsbackend.domain.dto.meals.program.MealDto;
import com.cokroktoupadek.beersandmealsbackend.domain.dto.user.CreatedUserDto;
import com.cokroktoupadek.beersandmealsbackend.domain.entity.beer.BeerEntity;
import com.cokroktoupadek.beersandmealsbackend.domain.entity.meal.MealEntity;
import com.cokroktoupadek.beersandmealsbackend.domain.entity.user.UserEntity;
import com.cokroktoupadek.beersandmealsbackend.errorhandlers.BeerDbIsEmptyException;
import com.cokroktoupadek.beersandmealsbackend.errorhandlers.MealDbIsEmptyException;
import com.cokroktoupadek.beersandmealsbackend.errorhandlers.UserCreationException;
import com.cokroktoupadek.beersandmealsbackend.mapper.Mapper;
import com.cokroktoupadek.beersandmealsbackend.service.beer.BeerDbService;
import com.cokroktoupadek.beersandmealsbackend.service.meal.MealDbService;
import com.cokroktoupadek.beersandmealsbackend.service.user.UserDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class UserFacade {


    private UserDbService userDbService;

    private BeerDbService beerDbService;

    private MealDbService mealDbService;

    private Mapper mapper;

    private PasswordEncoder encoder;
    @Autowired
    public UserFacade(UserDbService userDbService, BeerDbService beerDbService, Mapper mapper,MealDbService mealDbService) {
        this.userDbService = userDbService;
        this.beerDbService = beerDbService;
        this.mapper = mapper;
        this.mealDbService=mealDbService;
        this.encoder=new BCryptPasswordEncoder();
    }

    public String createUser(CreatedUserDto userDto) {

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
             entity.setPassword(encoder.encode(userDto.getPassword()));
             entity.setStatus(1);
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

    public List<MealDto> getMealList() throws MealDbIsEmptyException {
        List<MealEntity> mealEntityList= mealDbService.findAll();
        if (!mealEntityList.isEmpty()){
            return mapper.mapMealEntityToMealDtoList(mealEntityList);
        }else {
            throw new MealDbIsEmptyException();
        }
    }

    public List<BeerDto> getBeerFavoriteList(String login)  {
        Optional<UserEntity> userEntity= userDbService.findByLogin(login);
          return mapper.mapToBeerDtoList(userEntity.get().getFavouredBeers());
    }

    public List<MealDto> getMealFavoriteList(String login) {
        Optional<UserEntity> userEntity= userDbService.findByLogin(login);
        return mapper.mapMealEntityToMealDtoList(userEntity.get().getFavouredMeals());
    }
    //I don't check for finding user because it requires to be logged in
    public String addBeerToFavorite(String beerName, String login) {
        Optional<UserEntity> userEntity= userDbService.findByLogin(login);
        Optional<BeerEntity> beerEntity= beerDbService.findByName(beerName);
        if (beerEntity.isPresent()){
            if (!userEntity.get().getFavouredBeers().contains(beerEntity.get())){
                userEntity.get().getFavouredBeers().add(beerEntity.get());
                userDbService.save(userEntity.get());
                return "beer "+beerEntity.get().getName()+" has been added to your favorite list";
            }else{
                return "beer is already on your list";
            }
        }else {
            return "beer not found";
        }
    }

    public String addMealToFavorite(String mealName, String login) {
        Optional<UserEntity> userEntity= userDbService.findByLogin(login);
        Optional<MealEntity> mealEntity= mealDbService.findByName(mealName);
        if (mealEntity.isPresent()){
            if(!userEntity.get().getFavouredMeals().contains(mealEntity.get())){
                userEntity.get().getFavouredMeals().add(mealEntity.get());
                userDbService.save(userEntity.get());
                return "meal "+mealEntity.get().getName()+" has been added to your favorite list";
            }else {
                return "meal is already on your list";
            }
        }else {
            return "meal not found";
        }
    }

    public String removeMealFromFavorite(String mealName, String login) {
        Optional<UserEntity> userEntity = userDbService.findByLogin(login);
        Optional<MealEntity> mealEntity = mealDbService.findByName(mealName);
        if (mealEntity.isPresent()) {
            if (userEntity.get().getFavouredMeals().contains(mealEntity.get())) {
                userEntity.get().getFavouredMeals().remove(mealEntity.get());
                userDbService.save(userEntity.get());
                return "meal " + mealEntity.get().getName() + " has been removed from your favorite list";
            } else {
                return "Meal not found on your list";
            }
        }else{
            return "Meal not found in db. Check for typos";
        }
    }

        public String removeBeerFromFavorite (String beerName, String login) {
            Optional<UserEntity> userEntity = userDbService.findByLogin(login);
            Optional<BeerEntity> beerEntity = beerDbService.findByName(beerName);
            if (beerEntity.isPresent()) {
                if (userEntity.get().getFavouredBeers().contains(beerEntity.get())) {
                    userEntity.get().getFavouredBeers().remove(beerEntity.get());
                    userDbService.save(userEntity.get());
                    return "beer " + beerEntity.get().getName() + " has been removed from your favorite list";
                } else {
                    return "Beer not found on your list";
                }
            }else{
                return "Beer not found in db. Check for typos";
            }
        }
}
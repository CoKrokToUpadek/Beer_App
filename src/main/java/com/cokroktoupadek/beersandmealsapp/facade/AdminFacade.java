package com.cokroktoupadek.beersandmealsapp.facade;

import com.cokroktoupadek.beersandmealsapp.client.BeersAndMealsClient;
import com.cokroktoupadek.beersandmealsapp.domain.dto.beer.BeerDto;
import com.cokroktoupadek.beersandmealsapp.domain.dto.meals.api_request.SingleMealApiDto;
import com.cokroktoupadek.beersandmealsapp.domain.dto.meals.program.MealDto;
import com.cokroktoupadek.beersandmealsapp.domain.dto.user.UserDto;
import com.cokroktoupadek.beersandmealsapp.domain.entity.beer.BeerEntity;
import com.cokroktoupadek.beersandmealsapp.domain.entity.meal.MealEntity;
import com.cokroktoupadek.beersandmealsapp.domain.entity.user.UserEntity;
import com.cokroktoupadek.beersandmealsapp.errorhandlers.BeerNotFoundException;
import com.cokroktoupadek.beersandmealsapp.mapper.Mapper;
import com.cokroktoupadek.beersandmealsapp.mapper.BeerEntityFilterAndSaver;
import com.cokroktoupadek.beersandmealsapp.service.beer.*;
import com.cokroktoupadek.beersandmealsapp.service.meal.MealDbService;
import com.cokroktoupadek.beersandmealsapp.service.user.UserDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AdminFacade {


    private BeerDbService beerDbService;
    private Mapper mapper;
    private BeersAndMealsClient beersAndMealsClient;
    private BeerEntityFilterAndSaver beerEntityFilter;
    private BeerAndMealEntityManipulatorDbService beerAndMealEntityManipulatorDbService;
    private MealDbService mealDbService;

    private UserDbService userDbService;

    private PasswordEncoder encoder;

    Pattern BCRYPT_PATTERN = Pattern.compile("\\A\\$2a?\\$\\d\\d\\$[./0-9A-Za-z]{53}");

    @Autowired
    public AdminFacade(BeerDbService beerDbService, Mapper mapper, BeersAndMealsClient beersAndMealsClient,
                       BeerEntityFilterAndSaver beerEntityFilter, BeerAndMealEntityManipulatorDbService beerAndMealEntityManipulatorDbService,
                       MealDbService mealDbService, UserDbService userDbService) {
        this.beerDbService = beerDbService;
        this.mapper = mapper;
        this.beersAndMealsClient = beersAndMealsClient;
        this.beerEntityFilter = beerEntityFilter;
        this.beerAndMealEntityManipulatorDbService = beerAndMealEntityManipulatorDbService;
        this.mealDbService = mealDbService;
        this.userDbService = userDbService;
        this.encoder=new BCryptPasswordEncoder();
    }

    ///////////////////////////////meals////////////////////////////////////////////////
    public String updateMealDbFacade() {
        List<SingleMealApiDto> mealApiDtoList = beersAndMealsClient.getMealsDtoList();
        List<MealDto> mealDtoList = mapper.mapFromMealApiToMealDtoList(mealApiDtoList);
        List<MealEntity> mealEntityList = mapper.mapToMealDtoToEntityList(mealDtoList);
        for (MealEntity meal : mealEntityList) {
            mealDbService.mealDuplicateVerifyAndSave(meal);
        }
        return "meal list updated successfully";
    }

    public String updateSingleMealFacade(Long id) {
        SingleMealApiDto mealApiDto = beersAndMealsClient.getSingleMealDtoById(id);
        MealDto mealDto = mapper.mapFromApiDtoToMealDto(mealApiDto);
        MealEntity mealEntity = mapper.mapFromMealDtoToMealEntity(mealDto);
        mealDbService.mealDuplicateVerifyAndSave(mealEntity);
        return "meal updated successfully";
    }

    public String deleteSingleMeal(String name) {
        beerAndMealEntityManipulatorDbService.mealEntityDeleter(name);
        return "meal deleted successfully";
    }

    public String deleteAllMeals() {
        List<MealEntity> mealEntityList = mealDbService.findAll();
        if (mealEntityList.isEmpty()) {
            return "meal db is empty";
        } else {
            beerAndMealEntityManipulatorDbService.allMealsEntitiesDeleter(mealEntityList.stream().map(MealEntity::getName).collect(Collectors.toList()));
            return "all meals has been deleted successfully";
        }
    }

    ///////////////////////////////beers////////////////////////////////////////////////
    public String updateBeerDbFacade() {
        List<BeerDto> beerDtoList = beersAndMealsClient.getBeerDtoList();
        List<BeerEntity> beerEntities = mapper.mapToBeerEntityList(beerDtoList);
        for (BeerEntity beerEntity : beerEntities) {
            beerEntityFilter.beerEntitySaver(beerEntity);
        }
        return "beer list updated successfully";
    }

    public String updateSingleBeerFacade(int beerNo) {
        List<BeerDto> beerDto = beersAndMealsClient.getBeerDto(beerNo);
        BeerEntity beerEntities = mapper.mapToBeerEntity(beerDto.get(0));
        beerEntityFilter.beerEntitySaver(beerEntities);
        return "beer updated successfully";
    }

    public String deleteSingleBeer(String name) throws BeerNotFoundException {
        if (beerDbService.findByName(name).isEmpty()) {
            throw new BeerNotFoundException();
        }
        beerAndMealEntityManipulatorDbService.beerEntityDeleter(name);
        beerAndMealEntityManipulatorDbService.entitiesWithEmptyRelationsCleaner();
        return "beer was deleted successfully";
    }

    public String deleteAllBeers() {
        List<BeerEntity> beerEntityList = beerDbService.findAll();
        if (beerEntityList.isEmpty()) {
            return "db is empty";
        }
        beerAndMealEntityManipulatorDbService.allBeerEntitiesDeleter(beerEntityList.stream().map(BeerEntity::getName).collect(Collectors.toList()));
        beerAndMealEntityManipulatorDbService.entitiesWithEmptyRelationsCleaner();
        return "all beers has been deleted successfully";
    }

    public String setUserRole(String login, String role) {
        Optional<UserEntity> userEntity = userDbService.findByLogin(login);
        if (userEntity.isPresent()) {
            switch (role) {
                case "admin":
                    userEntity.get().setUserRole(role);
                    userDbService.save(userEntity.get());
                    return "User with login:" + userEntity.get().getLogin() + " is now admin";
                case "user":
                    userEntity.get().setUserRole(role);
                    userDbService.save(userEntity.get());
                    return "User with login:" + userEntity.get().getLogin() + " is now user";
                default:
                    return "specified role does not exist";
            }
        } else {
            return "user with login: " + login + " not found.";
        }
    }

    public String setUserStatus(String login, Integer status) {
        Optional<UserEntity> userEntity = userDbService.findByLogin(login);
        if (userEntity.isPresent()) {
            switch (status) {
                case 0:
                    userEntity.get().setStatus(status);
                    userDbService.save(userEntity.get());
                    return "User with login:" + userEntity.get().getLogin() + " is now inactive";
                case 1:
                    userEntity.get().setStatus(status);
                    userDbService.save(userEntity.get());
                    return "User with login:" + userEntity.get().getLogin() + " is now active";
                default:
                    return "status can be either 0 for banned, or 1 for active";
            }
        }else {
            return "user with login: " + login + " not found.";
        }
    }

    public  List<UserDto>getUsers() {
        List<UserEntity> userEntityList=userDbService.findAll();
        return mapper.mapToUserDtoList(userEntityList);
    }

    public String encodePassword(String login) {
        Optional<UserEntity> userEntity = userDbService.findByLogin(login);
        if (userEntity.isPresent()) {
            if (!BCRYPT_PATTERN.matcher(userEntity.get().getPassword()).matches()){
                String tempPass=userEntity.get().getPassword();
                userEntity.get().setPassword(encoder.encode(tempPass));
                userDbService.save(userEntity.get());
                return "password for user"+userEntity.get().getLogin() +" has been encoded successfully";
            }
            else {
                return "user password has already been encoded";
            }

        }else {
            return "user with login"+login+" was not found";
        }
    }
}
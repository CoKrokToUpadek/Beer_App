package com.cokroktoupadek.beersandmealsapp.facade;

import com.cokroktoupadek.beersandmealsapp.client.BeersAndMealsClient;
import com.cokroktoupadek.beersandmealsapp.domain.dto.beer.BeerDto;
import com.cokroktoupadek.beersandmealsapp.domain.dto.meals.api_request.SingleMealApiDto;
import com.cokroktoupadek.beersandmealsapp.domain.dto.meals.program.MealDto;
import com.cokroktoupadek.beersandmealsapp.domain.entity.beer.BeerEntity;
import com.cokroktoupadek.beersandmealsapp.domain.entity.meal.MealEntity;
import com.cokroktoupadek.beersandmealsapp.errorhandlers.BeerNotFoundException;
import com.cokroktoupadek.beersandmealsapp.mapper.Mapper;
import com.cokroktoupadek.beersandmealsapp.mapper.BeerEntityFilterAndSaver;
import com.cokroktoupadek.beersandmealsapp.service.beer.*;
import com.cokroktoupadek.beersandmealsapp.service.meal.MealDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AdminFacade {


    private BeerDbService beerDbService;
    private Mapper mapper;
    private BeersAndMealsClient beersAndMealsClient;
    private  BeerEntityFilterAndSaver beerEntityFilter;
    private BeerEntityManipulatorDbService beerEntityManipulatorDbService;
    private MealDbService mealDbService;
    @Autowired
    public AdminFacade(BeerDbService beerDbService, Mapper mapper, BeersAndMealsClient beersAndMealsClient,
                       BeerEntityFilterAndSaver beerEntityFilter, BeerEntityManipulatorDbService beerEntityManipulatorDbService,
                       MealDbService mealDbService) {
        this.beerDbService = beerDbService;
        this.mapper = mapper;
        this.beersAndMealsClient = beersAndMealsClient;
        this.beerEntityFilter = beerEntityFilter;
        this.beerEntityManipulatorDbService = beerEntityManipulatorDbService;
        this.mealDbService = mealDbService;
    }



    ///////////////////////////////meals////////////////////////////////////////////////
    public String updateMealDbFacade(){
        List<SingleMealApiDto> mealApiDtoList=beersAndMealsClient.getMealsDtoList();
        List<MealDto> mealDtoList=mapper.mapFromMealApiToMealDtoList(mealApiDtoList);
        List<MealEntity> mealEntityList=mapper.mapToMealDtoToEntityList(mealDtoList);
        for (MealEntity meal :mealEntityList){
            mealDbService.save(meal);
        }
        return "meal list updated successfully";
    }
    public String updateSingleMealFacade(Long id){
        SingleMealApiDto mealApiDto=beersAndMealsClient.getSingleMealDtoById(id);
        MealDto mealDto=mapper.mapFromApiDtoToMealDto(mealApiDto);
        MealEntity mealEntity= mapper.mapFromMealDtoToMealEntity(mealDto);
        mealDbService.save(mealEntity);
        return "meal updated successfully";
    }

    public String deleteSingleMealById(Long id){
        mealDbService.deleteById(id);
        return "meal deleted successfully";
    }

    public String deleteAllMeals(){
        mealDbService.deleteAll();
        return "meal deleted successfully";
    }

    ///////////////////////////////beers////////////////////////////////////////////////
    public String updateBeerDbFacade(){
        List<BeerDto> beerDtoList= beersAndMealsClient.getBeerDtoList();
        List<BeerEntity> beerEntities= mapper.mapToBeerEntityList(beerDtoList);
        for (BeerEntity beerEntity:beerEntities){
            beerEntityFilter.beerEntitySaver(beerEntity);
        }
        return "beer list updated successfully";
    }

    public String updateSingleBeerFacade(int beerNo){
        List<BeerDto> beerDto= beersAndMealsClient.getBeerDto(beerNo);
        BeerEntity beerEntities= mapper.mapToBeerEntity(beerDto.get(0));
        beerEntityFilter.beerEntitySaver(beerEntities);
        return "beer updated successfully";
    }

    public String deleteSingleBeer(String name) throws BeerNotFoundException{
        if (beerDbService.findByName(name).isEmpty()){
            throw new BeerNotFoundException();
        }
        beerEntityManipulatorDbService.beerEntityDeleter(name);
        beerEntityManipulatorDbService.entitiesWithEmptyRelationsCleaner();
        return "beer was deleted successfully";
    }

    public String deleteAllBeers() {
        List<BeerEntity> beerEntityList= beerDbService.findAll();
        if(beerEntityList.isEmpty()){
            return "db is empty";
        }
        beerEntityManipulatorDbService.allBeerEntitiesDeleter(beerEntityList.stream().map(BeerEntity::getName).collect(Collectors.toList()));
        beerEntityManipulatorDbService.entitiesWithEmptyRelationsCleaner();
        return "all beers has been deleted successfully";
    }

}

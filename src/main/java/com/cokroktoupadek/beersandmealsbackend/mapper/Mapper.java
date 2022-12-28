package com.cokroktoupadek.beersandmealsbackend.mapper;


import com.cokroktoupadek.beersandmealsbackend.domain.dto.beer.BeerDto;
import com.cokroktoupadek.beersandmealsbackend.domain.dto.meals.api_request.SingleMealApiDto;
import com.cokroktoupadek.beersandmealsbackend.domain.dto.meals.program.MealDto;
import com.cokroktoupadek.beersandmealsbackend.domain.dto.user.CreatedUserDto;
import com.cokroktoupadek.beersandmealsbackend.domain.dto.user.UserDto;
import com.cokroktoupadek.beersandmealsbackend.domain.entity.beer.BeerEntity;


import com.cokroktoupadek.beersandmealsbackend.domain.entity.meal.MealEntity;
import com.cokroktoupadek.beersandmealsbackend.domain.entity.user.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class Mapper {
    
    ModelMapper modelMapper;
    @Autowired
    public Mapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


   public List<MealDto> mapMealEntityToMealDtoList(List<MealEntity> mealEntityList){
        return mealEntityList.stream()
                .map(this::mapMealEntityToMealDto)
                .collect(Collectors.toList());
    }

    public MealDto mapMealEntityToMealDto(MealEntity mealEntity){
        return modelMapper.map(mealEntity,MealDto.class);

    }

    public List<MealEntity> mapToMealDtoToEntityList(final List<MealDto> mealEntityList) {
        return mealEntityList.stream()
                .map(this::mapFromMealDtoToMealEntity)
                .collect(Collectors.toList());
    }

    public MealEntity mapFromMealDtoToMealEntity(MealDto mealDto){
        return modelMapper.map(mealDto,MealEntity.class);
    }

    public List<MealDto> mapFromMealApiToMealDtoList(final List<SingleMealApiDto> mealsApiDtoList){
        return mealsApiDtoList.stream().map(this::mapFromApiDtoToMealDto).collect(Collectors.toList());
    }
    public MealDto mapFromApiDtoToMealDto(SingleMealApiDto singleMealApiDto){
        return modelMapper.map(singleMealApiDto,MealDto.class);
    }


    public BeerEntity mapToBeerEntity(BeerDto beerDto){
        return modelMapper.map(beerDto,BeerEntity.class);
    }

    public BeerDto mapToBeerDto(BeerEntity beerEntity){
        return modelMapper.map(beerEntity,BeerDto.class);

    }

    public List<BeerDto> mapToBeerDtoList(final List<BeerEntity> beerEntityList) {
        return beerEntityList.stream()
                .map(this::mapToBeerDto)
                .collect(Collectors.toList());
    }

    public List<BeerEntity> mapToBeerEntityList(final List<BeerDto> beerDtoList) {
        return beerDtoList.stream()
                .map(this::mapToBeerEntity)
                .collect(Collectors.toList());
    }

    public List<UserDto> mapToUserDtoList(List<UserEntity> userEntityList){
        return userEntityList.stream().map(this::mapToUserDto).collect(Collectors.toList());
    }

    public UserDto mapToUserDto(UserEntity userEntity){
        return modelMapper.map(userEntity,UserDto.class);
    }

    public CreatedUserDto mapToCreatedUserDto(UserEntity userEntity){
        return modelMapper.map(userEntity,CreatedUserDto.class);
    }

    public UserEntity mapUserEntity(CreatedUserDto createdUserDto){
        return modelMapper.map(createdUserDto,UserEntity.class);
    }

    public UserEntity mapNewUserEntity(CreatedUserDto createdUserDto){
        return new UserEntity(createdUserDto.getFirstName(), createdUserDto.getLastName(),
                   createdUserDto.getAddress(), createdUserDto.getEmail(), createdUserDto.getLogin(),
                   createdUserDto.getPassword(),"user", LocalDate.now());
    }



}

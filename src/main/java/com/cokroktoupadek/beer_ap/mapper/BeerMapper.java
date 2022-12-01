package com.cokroktoupadek.beer_ap.mapper;


import com.cokroktoupadek.beer_ap.domain.dto.beer.BeerDto;
import com.cokroktoupadek.beer_ap.domain.dto.user.CreatedUserDto;
import com.cokroktoupadek.beer_ap.domain.entity.beer.BeerEntity;

import com.cokroktoupadek.beer_ap.domain.entity.user.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BeerMapper {

    //ModelMapper modelMapper=BeerMapperSingleton.INSTANCE.getModelMapper();
    ModelMapper modelMapper=BeerMapperSingleton.getInstance().modelMapper;


    public BeerDto mapToBeerDto(BeerEntity beerEntity){
        BeerDto beerDto=modelMapper.map(beerEntity,BeerDto.class);
        return beerDto;

    }

    public BeerEntity mapToBeerEntity(BeerDto beerDto){
        BeerEntity beerEntity=modelMapper.map(beerDto,BeerEntity.class);
        return beerEntity;
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


    public CreatedUserDto mapToUserDto(UserEntity userEntity){
        CreatedUserDto createdUserDto=modelMapper.map(userEntity,CreatedUserDto.class);
        return createdUserDto;
    }

    public UserEntity mapUserEntity(CreatedUserDto createdUserDto){
        UserEntity userEntity=modelMapper.map(createdUserDto,UserEntity.class);
        return userEntity;
    }

    public UserEntity mapNewUserEntity(CreatedUserDto createdUserDto){
        return new UserEntity(createdUserDto.getFirstName(), createdUserDto.getLastName(),
                   createdUserDto.getAddress(), createdUserDto.getEmail(), createdUserDto.getLogin(),
                   createdUserDto.getPassword(),"user", LocalDate.now());
    }



}

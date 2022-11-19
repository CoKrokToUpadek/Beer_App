package com.cokroktoupadek.beer_ap.mapper;


import com.cokroktoupadek.beer_ap.domain.dto.beer.BeerDto;
import com.cokroktoupadek.beer_ap.domain.entity.beer.BeerEntity;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class BeerMapper {



    ModelMapper modelMapper=BeerMapperSingleton.getInstance().modelMapper;


    public BeerDto mapToBeerDto(BeerEntity beerEntity){

        BeerDto beerDto=modelMapper.map(beerEntity,BeerDto.class);

        return beerDto;

    }

    public BeerEntity mapToBeerEntity(BeerDto beerDto){
        BeerEntity beerEntity=modelMapper.map(beerDto,BeerEntity.class);
        return beerEntity;
    }



}

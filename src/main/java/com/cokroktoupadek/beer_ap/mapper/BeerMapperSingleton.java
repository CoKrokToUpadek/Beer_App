package com.cokroktoupadek.beer_ap.mapper;
import com.cokroktoupadek.beer_ap.domain.dto.beer.*;
import com.cokroktoupadek.beer_ap.domain.entity.beer.*;
import org.modelmapper.ModelMapper;

import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class BeerMapperSingleton {
    public static ModelMapper modelMapper=new ModelMapper();
    private static BeerMapperSingleton instance=null;

    @PostConstruct
    static void configuration(){
        modelMapper.typeMap(BeerEntity.class, BeerDto.class).addMappings(mapper -> mapper.map(BeerEntity::getVolume,BeerDto::setVolumeDto));
        modelMapper.typeMap(BeerEntity.class, BeerDto.class).addMappings(mapper -> mapper.map(BeerEntity::getBoilVolume,BeerDto::setBoilVolumeDto));
        modelMapper.typeMap(BeerEntity.class, BeerDto.class).addMappings(mapper -> mapper.map(BeerEntity::getMethod,BeerDto::setMethodDto));
        modelMapper.typeMap(BeerEntity.class, BeerDto.class).addMappings(mapper -> mapper.map(BeerEntity::getIngredients,BeerDto::setIngredientsDto));

        modelMapper.typeMap(MethodEntity.class, MethodDto.class).addMappings(mapper -> mapper.map(MethodEntity::getFermentation,MethodDto::setFermentationDto));
        modelMapper.typeMap(MethodEntity.class, MethodDto.class).addMappings(mapper -> mapper.map(MethodEntity::getMashTempsList,MethodDto::setMashTempDtoList));

        modelMapper.typeMap(MashTempEntity.class, MashTempDto.class).addMappings(mapper -> mapper.map(MashTempEntity::getTemp,MashTempDto::setTempDto));
        modelMapper.typeMap(FermentationEntity.class, FermentationDto.class).addMappings(mapper -> mapper.map(FermentationEntity::getTemp,FermentationDto::setTempDto));

        modelMapper.typeMap(IngredientsEntity.class, IngredientsDto.class).addMappings(mapper -> mapper.map(IngredientsEntity::getMaltsList,IngredientsDto::setMaltDtoList));
        modelMapper.typeMap(IngredientsEntity.class, IngredientsDto.class).addMappings(mapper -> mapper.map(IngredientsEntity::getHopsList,IngredientsDto::setHopsDtoList));

        modelMapper.typeMap(MaltEntity.class, MaltDto.class).addMappings(mapper -> mapper.map(MaltEntity::getAmount,MaltDto::setAmountDto));
        modelMapper.typeMap(HopsEntity.class, HopsDto.class).addMappings(mapper -> mapper.map(HopsEntity::getAmount,HopsDto::setAmountDto));

        //reversed mapping
         modelMapper.typeMap( IngredientsDto.class,IngredientsEntity.class).addMappings(mapper -> mapper.map(IngredientsDto::getMaltDtoList,IngredientsEntity::setMaltsList));//działa
         modelMapper.typeMap(MethodDto.class,MethodEntity.class).addMappings(mapper -> mapper.map(MethodDto::getMashTempDtoList,MethodEntity::setMashTempsList));//działa

        //modelMapper.typeMap(MashTempDto.class,MashTempEntity.class).addMappings(mapper -> mapper.map(MashTempDto::getTempDto,MashTempEntity::setTemp));
    }

    public static BeerMapperSingleton getInstance() {
        if (instance==null){
            instance=new BeerMapperSingleton();
        }
        return instance;
    }
}

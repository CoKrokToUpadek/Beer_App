package com.cokroktoupadek.beer_ap.mapper;
import com.cokroktoupadek.beer_ap.domain.dto.beer.*;
import com.cokroktoupadek.beer_ap.domain.entity.beer.*;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;


import org.springframework.context.annotation.Configuration;


import javax.annotation.PostConstruct;





public class BeerMapperSingleton {


   public ModelMapper configuration(){

         ModelMapper modelMapper=new ModelMapper();


        Converter<Double,Double> converterDouble=ctx->{
            if (ctx.getSource()==null){
                return 0d;
            }else {
                return ctx.getSource();
            }
        };

        Converter<Integer,Integer> converterInt=ctx->{
            if (ctx.getSource()==null){
                return 0;
            }else {
                return ctx.getSource();
            }
        };
        //insert zeros in fields that are null but should not be (api have some holes)
        modelMapper.typeMap(BeerDto.class,BeerEntity.class).addMappings(mapper->mapper.using(converterDouble).map(BeerDto::getPh,BeerEntity::setPh));
        modelMapper.typeMap(BeerDto.class,BeerEntity.class).addMappings(mapper->mapper.using(converterDouble).map(BeerDto::getEbc,BeerEntity::setEbc));
        modelMapper.typeMap(BeerDto.class,BeerEntity.class).addMappings(mapper->mapper.using(converterDouble).map(BeerDto::getSrm,BeerEntity::setSrm));
        modelMapper.typeMap(BeerDto.class,BeerEntity.class).addMappings(mapper->mapper.using(converterDouble).map(BeerDto::getIbu,BeerEntity::setIbu));
        modelMapper.typeMap(MashTempDto.class,MashTempEntity.class).addMappings(mapper->mapper.using(converterInt).map(MashTempDto::getDuration,MashTempEntity::setDuration));

        //entity->dto missing fields
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

        //dto->entity missing fields
        modelMapper.typeMap(BeerDto.class, BeerEntity.class).addMappings(mapper -> mapper.map(BeerDto::getMethodDto,BeerEntity::setMethod));
        modelMapper.typeMap(BeerDto.class, BeerEntity.class).addMappings(mapper -> mapper.map(BeerDto::getIngredientsDto,BeerEntity::setIngredients));
        modelMapper.typeMap(MethodDto.class, MethodEntity.class).addMappings(mapper -> mapper.map(MethodDto::getFermentationDto,MethodEntity::setFermentation));
        modelMapper.typeMap(MethodDto.class, MethodEntity.class).addMappings(mapper -> mapper.map(MethodDto::getMashTempDtoList,MethodEntity::setMashTempsList));
        modelMapper.typeMap(MashTempDto.class, MashTempEntity.class).addMappings(mapper -> mapper.map(MashTempDto::getTempDto,MashTempEntity::setTemp));
        modelMapper.typeMap(FermentationDto.class, FermentationEntity.class).addMappings(mapper -> mapper.map(FermentationDto::getTempDto,FermentationEntity::setTemp));
        modelMapper.typeMap(IngredientsDto.class, IngredientsEntity.class).addMappings(mapper -> mapper.map(IngredientsDto::getMaltDtoList,IngredientsEntity::setMaltsList));
        modelMapper.typeMap(IngredientsDto.class, IngredientsEntity.class).addMappings(mapper -> mapper.map(IngredientsDto::getHopsDtoList,IngredientsEntity::setHopsList));
        modelMapper.typeMap(MaltDto.class, MaltEntity.class).addMappings(mapper -> mapper.map(MaltDto::getAmountDto,MaltEntity::setAmount));
        modelMapper.typeMap(HopsDto.class, HopsEntity.class).addMappings(mapper -> mapper.map(HopsDto::getAmountDto,HopsEntity::setAmount));
        return modelMapper;
    }


}
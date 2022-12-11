package com.cokroktoupadek.beersandmealsapp.mapper;
import com.cokroktoupadek.beersandmealsapp.domain.dto.beer.*;
import com.cokroktoupadek.beersandmealsapp.domain.dto.meals.api_request.SingleMealApiDto;
import com.cokroktoupadek.beersandmealsapp.domain.dto.meals.program.IngredientAndMeasureDto;
import com.cokroktoupadek.beersandmealsapp.domain.dto.meals.program.MealDto;
import com.cokroktoupadek.beersandmealsapp.domain.entity.beer.*;

import com.cokroktoupadek.beersandmealsapp.domain.entity.meal.IngredientAndMeasureEntity;
import com.cokroktoupadek.beersandmealsapp.domain.entity.meal.MealEntity;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;

import java.util.ArrayList;


public class BeerMapperConfigurator {

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


       Converter<SingleMealApiDto, MealDto> converterApiDtoToDto = new Converter<>() {
           @Override
           public MealDto convert(MappingContext<SingleMealApiDto, MealDto> ctx) {
               //dunno if its model mapper thing while trying to use ctx.getDestination mapper throws null pointers while trying to use this converter
               MealDto mealDto=new MealDto();
               mealDto.setId(ctx.getSource().getId());
               mealDto.setName(ctx.getSource().getName()+"firma jp");
               mealDto.setCategory(ctx.getSource().getCategory());
               mealDto.setArea(ctx.getSource().getArea());
               mealDto.setInstruction(ctx.getSource().getInstruction());
               mealDto.setThumbnail(ctx.getSource().getThumbnail());
               mealDto.setThumbnail(ctx.getSource().getThumbnail());
               mealDto.setTags(ctx.getSource().getTags());
               mealDto.setYoutubeLink(ctx.getSource().getYoutubeLink());
               mealDto.setSource(ctx.getSource().getSource());
               mealDto.setIngredientsAndMeasureDtoList(new ArrayList<>());

               if (ctx.getSource().getIngredient1()!=null){
                   mealDto.getIngredientsAndMeasureDtoList().add(new IngredientAndMeasureDto(ctx.getSource().getIngredient1(),ctx.getSource().getMeasure1()));
               }
               if (ctx.getSource().getIngredient2()!=null){
                   mealDto.getIngredientsAndMeasureDtoList().add(new IngredientAndMeasureDto(ctx.getSource().getIngredient2(),ctx.getSource().getMeasure2()));
               }
               if (ctx.getSource().getIngredient3()!=null){
                   mealDto.getIngredientsAndMeasureDtoList().add(new IngredientAndMeasureDto(ctx.getSource().getIngredient3(),ctx.getSource().getMeasure3()));
               }
               if (ctx.getSource().getIngredient4()!=null){
                   mealDto.getIngredientsAndMeasureDtoList().add(new IngredientAndMeasureDto(ctx.getSource().getIngredient4(),ctx.getSource().getMeasure4()));
               }
               if (ctx.getSource().getIngredient5()!=null){
                   mealDto.getIngredientsAndMeasureDtoList().add(new IngredientAndMeasureDto(ctx.getSource().getIngredient5(),ctx.getSource().getMeasure5()));
               }
               if (ctx.getSource().getIngredient6()!=null){
                   mealDto.getIngredientsAndMeasureDtoList().add(new IngredientAndMeasureDto(ctx.getSource().getIngredient6(),ctx.getSource().getMeasure6()));
               }
               if (ctx.getSource().getIngredient7()!=null){
                   mealDto.getIngredientsAndMeasureDtoList().add(new IngredientAndMeasureDto(ctx.getSource().getIngredient7(),ctx.getSource().getMeasure7()));
               }
               if (ctx.getSource().getIngredient8()!=null){
                   mealDto.getIngredientsAndMeasureDtoList().add(new IngredientAndMeasureDto(ctx.getSource().getIngredient8(),ctx.getSource().getMeasure8()));
               }
               if (ctx.getSource().getIngredient9()!=null){
                   mealDto.getIngredientsAndMeasureDtoList().add(new IngredientAndMeasureDto(ctx.getSource().getIngredient9(),ctx.getSource().getMeasure9()));
               }
               if (ctx.getSource().getIngredient10()!=null){
                   mealDto.getIngredientsAndMeasureDtoList().add(new IngredientAndMeasureDto(ctx.getSource().getIngredient10(),ctx.getSource().getMeasure10()));
               }
               if (ctx.getSource().getIngredient11()!=null){
                   mealDto.getIngredientsAndMeasureDtoList().add(new IngredientAndMeasureDto(ctx.getSource().getIngredient11(),ctx.getSource().getMeasure11()));
               }
               if (ctx.getSource().getIngredient12()!=null){
                   mealDto.getIngredientsAndMeasureDtoList().add(new IngredientAndMeasureDto(ctx.getSource().getIngredient12(),ctx.getSource().getMeasure12()));
               }
               if (ctx.getSource().getIngredient13()!=null){
                   mealDto.getIngredientsAndMeasureDtoList().add(new IngredientAndMeasureDto(ctx.getSource().getIngredient13(),ctx.getSource().getMeasure13()));
               }
               if (ctx.getSource().getIngredient14()!=null){
                   mealDto.getIngredientsAndMeasureDtoList().add(new IngredientAndMeasureDto(ctx.getSource().getIngredient14(),ctx.getSource().getMeasure14()));
               }
               if (ctx.getSource().getIngredient15()!=null){
                   mealDto.getIngredientsAndMeasureDtoList().add(new IngredientAndMeasureDto(ctx.getSource().getIngredient15(),ctx.getSource().getMeasure15()));
               }
               if (ctx.getSource().getIngredient16()!=null){
                   mealDto.getIngredientsAndMeasureDtoList().add(new IngredientAndMeasureDto(ctx.getSource().getIngredient16(),ctx.getSource().getMeasure16()));
               }
               if (ctx.getSource().getIngredient17()!=null){
                   mealDto.getIngredientsAndMeasureDtoList().add(new IngredientAndMeasureDto(ctx.getSource().getIngredient17(),ctx.getSource().getMeasure17()));
               }
               if (ctx.getSource().getIngredient18()!=null){
                   mealDto.getIngredientsAndMeasureDtoList().add(new IngredientAndMeasureDto(ctx.getSource().getIngredient18(),ctx.getSource().getMeasure18()));
               }
               if (ctx.getSource().getIngredient19()!=null){
                   mealDto.getIngredientsAndMeasureDtoList().add(new IngredientAndMeasureDto(ctx.getSource().getIngredient19(),ctx.getSource().getMeasure19()));
               }
               if (ctx.getSource().getIngredient20()!=null){
                   mealDto.getIngredientsAndMeasureDtoList().add(new IngredientAndMeasureDto(ctx.getSource().getIngredient20(),ctx.getSource().getMeasure20()));
               }
               return mealDto;
           }
       };

       Converter<MealDto, MealEntity> converterDtoToEntity = new Converter<>() {

           @Override
           public MealEntity convert(MappingContext<MealDto, MealEntity> ctx) {
               MealEntity mealEntity=new MealEntity();
               mealEntity.setId(ctx.getSource().getId());
               mealEntity.setName(ctx.getSource().getName());
               mealEntity.setCategory(ctx.getSource().getCategory());
               mealEntity.setArea(ctx.getSource().getArea());
               mealEntity.setInstruction(ctx.getSource().getInstruction());
               mealEntity.setThumbnail(ctx.getSource().getThumbnail());
               mealEntity.setTags(ctx.getSource().getTags());
               mealEntity.setYoutubeLink(ctx.getSource().getYoutubeLink());
               mealEntity.setSource(ctx.getSource().getSource());
               mealEntity.setIngredientsAndMeasureEntityList(new ArrayList<>());
               for (IngredientAndMeasureDto dto :ctx.getSource().getIngredientsAndMeasureDtoList()){
                   mealEntity.getIngredientsAndMeasureEntityList().add(new IngredientAndMeasureEntity(dto.getIngredientName(),dto.getIngredientMeasure()));
               }
               return mealEntity;
           }
       };

       //dto->entity
       modelMapper.addConverter(converterDtoToEntity);

       //MealApiDto->mealDto
       modelMapper.addConverter(converterApiDtoToDto);


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
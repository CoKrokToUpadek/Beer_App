package com.cokroktoupadek.beersandmealsapp.h2_test_db.mapper;

import com.cokroktoupadek.beersandmealsapp.domain.dto.beer.BeerDto;
import com.cokroktoupadek.beersandmealsapp.domain.dto.meals.api_request.SingleMealApiDto;
import com.cokroktoupadek.beersandmealsapp.domain.dto.meals.program.MealDto;
import com.cokroktoupadek.beersandmealsapp.domain.entity.beer.*;
import com.cokroktoupadek.beersandmealsapp.mapper.Mapper;
import com.cokroktoupadek.beersandmealsapp.repository.beer.BeerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
@TestPropertySource("classpath:application-H2TestDb.properties")
class MapperTest {

    @Autowired
    Mapper mapper;

    @Autowired
    BeerRepository beerRepository;

    @Test
    void mapToBeerDto() {
        List<String> pairingList=new ArrayList<>(List.of("pair1","pair2"));
        AmountEntity amountEntity=new AmountEntity(1.0,"testAmount");
        MaltEntity maltEntity=new MaltEntity("testMalt",amountEntity);
        HopsEntity hopsEntity =new HopsEntity("testName",amountEntity,"testAdd","testAttribute");
        List<MaltEntity> maltEntityList=new ArrayList<>(List.of(maltEntity));
        List<HopsEntity> hopsEntityList=new ArrayList<>(List.of(hopsEntity));
        IngredientsEntity ingredientsEntity=new IngredientsEntity(maltEntityList,hopsEntityList,"testYeast");
        TempEntity tempEntity=new TempEntity(1,"testTemp");
        MashTempEntity mashTempEntity =new MashTempEntity(tempEntity,1);
        MashTempEntity mashTempEntity2 =new MashTempEntity(tempEntity,2);
        List<MashTempEntity> mashTempEntityList=new ArrayList<>(List.of(mashTempEntity,mashTempEntity2));
        FermentationEntity fermentationEntity=new FermentationEntity(new TempEntity(1,"testTemp"));
        MethodEntity methodEntity =new MethodEntity(mashTempEntityList,fermentationEntity);
        VolumeEntity volumeEntity=new VolumeEntity(1,"testUnit");
        BoilVolumeEntity boilVolumeEntity=new BoilVolumeEntity(1,"testUnit");
        BeerEntity beerEntity=new BeerEntity("testDescription","testUrl",1.0,1.0,1,1,1.0,1.0,1.0,1.0,
                volumeEntity, boilVolumeEntity, methodEntity,ingredientsEntity,pairingList,"test-tip","test author");
        beerRepository.save(beerEntity);
        Long id=beerEntity.getId();
        Optional<BeerEntity> fetchedBeerEntity = beerRepository.findById(id);
        //when
        BeerDto beerDto= mapper.mapToBeerDto(fetchedBeerEntity.get());
        //then
        assertNotNull(beerDto);
        //dunno what to test here-maybe live app would help

    }

    @Test
    void mapToBeerEntity() {
        List<String> pairingList=new ArrayList<>(List.of("pair1","pair2"));
        AmountEntity amountEntity=new AmountEntity(1.0,"testAmount");
        MaltEntity maltEntity=new MaltEntity("testMalt",amountEntity);
        HopsEntity hopsEntity =new HopsEntity("testName",amountEntity,"testAdd","testAttribute");
        List<MaltEntity> maltEntityList=new ArrayList<>(List.of(maltEntity));
        List<HopsEntity> hopsEntityList=new ArrayList<>(List.of(hopsEntity));
        IngredientsEntity ingredientsEntity=new IngredientsEntity(maltEntityList,hopsEntityList,"testYeast");
        TempEntity tempEntity=new TempEntity(1,"testTemp");
        MashTempEntity mashTempEntity =new MashTempEntity(tempEntity,1);
        MashTempEntity mashTempEntity2 =new MashTempEntity(tempEntity,2);
        List<MashTempEntity> mashTempEntityList=new ArrayList<>(List.of(mashTempEntity,mashTempEntity2));
        FermentationEntity fermentationEntity=new FermentationEntity(new TempEntity(1,"testTemp"));
        MethodEntity methodEntity =new MethodEntity(mashTempEntityList,fermentationEntity);
        VolumeEntity volumeEntity=new VolumeEntity(1,"testUnit");
        BoilVolumeEntity boilVolumeEntity=new BoilVolumeEntity(1,"testUnit");
        BeerEntity beerEntity=new BeerEntity("testDescription","testUrl",1.0,1.0,1,1,1.0,1.0,1.0,1.0,
                volumeEntity, boilVolumeEntity, methodEntity,ingredientsEntity,pairingList,"test-tip","test author");
        beerRepository.save(beerEntity);
        Long id=beerEntity.getId();
        Optional<BeerEntity> fetchedBeerEntity = beerRepository.findById(id);
        BeerDto beerDto= mapper.mapToBeerDto(fetchedBeerEntity.get());
        //when
        BeerEntity convertedBeerEntity= mapper.mapToBeerEntity(beerDto);
        //then
        assertNotNull(convertedBeerEntity);
        assertEquals(convertedBeerEntity, beerEntity);
    }

    @Test
    void mapToMealApiDtoToDto() {
        //given
        SingleMealApiDto singleMealApiDto=new SingleMealApiDto();
        singleMealApiDto.setId(1L);
        singleMealApiDto.setName("test name");
        singleMealApiDto.setCategory("test category");
        singleMealApiDto.setArea("test area");
        singleMealApiDto.setInstruction("test instructions");
        singleMealApiDto.setThumbnail("test thumbnail");
        singleMealApiDto.setTags("test tags");
        singleMealApiDto.setYoutubeLink("test youtube");
        singleMealApiDto.setSource("test source");
        singleMealApiDto.setIngredient1("i1");
        singleMealApiDto.setIngredient2("i2");
        singleMealApiDto.setIngredient3("i3");
        singleMealApiDto.setMeasure1("m1");
        singleMealApiDto.setMeasure2("m2");
        singleMealApiDto.setMeasure3("m3");
        //when
        MealDto mealDto=mapper.mapFromApiDtoToMealDto(singleMealApiDto);
        //then
        Assertions.assertEquals(3,mealDto.getIngredientsAndMeasureDtoList().size());
        Assertions.assertEquals(singleMealApiDto.getId(),mealDto.getId());
        Assertions.assertEquals(singleMealApiDto.getIngredient2(),mealDto.getIngredientsAndMeasureDtoList().get(1).getIngredientName());
    }
}
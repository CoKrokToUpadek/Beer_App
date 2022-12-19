package com.cokroktoupadek.beersandmealsapp.h2_test_db.mapper;

import com.cokroktoupadek.beersandmealsapp.domain.dto.beer.BeerDto;
import com.cokroktoupadek.beersandmealsapp.domain.dto.meals.api_request.SingleMealApiDto;

import com.cokroktoupadek.beersandmealsapp.domain.dto.meals.program.IngredientAndMeasureDto;
import com.cokroktoupadek.beersandmealsapp.domain.dto.meals.program.MealDto;
import com.cokroktoupadek.beersandmealsapp.domain.entity.beer.*;

import com.cokroktoupadek.beersandmealsapp.domain.entity.meal.IngredientAndMeasureEntity;
import com.cokroktoupadek.beersandmealsapp.domain.entity.meal.MealEntity;
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
        List<String> pairingList = new ArrayList<>(List.of("pair1", "pair2"));
        AmountEntity amountEntity = new AmountEntity(1.0, "testAmount");
        MaltEntity maltEntity = new MaltEntity("testMalt", amountEntity);
        HopsEntity hopsEntity = new HopsEntity("testName", amountEntity, "testAdd", "testAttribute");
        List<MaltEntity> maltEntityList = new ArrayList<>(List.of(maltEntity));
        List<HopsEntity> hopsEntityList = new ArrayList<>(List.of(hopsEntity));
        IngredientsEntity ingredientsEntity = new IngredientsEntity(maltEntityList, hopsEntityList, "testYeast");
        TempEntity tempEntity = new TempEntity(1, "testTemp");
        MashTempEntity mashTempEntity = new MashTempEntity(tempEntity, 1);
        MashTempEntity mashTempEntity2 = new MashTempEntity(tempEntity, 2);
        List<MashTempEntity> mashTempEntityList = new ArrayList<>(List.of(mashTempEntity, mashTempEntity2));
        FermentationEntity fermentationEntity = new FermentationEntity(new TempEntity(1, "testTemp"));
        MethodEntity methodEntity = new MethodEntity(mashTempEntityList, fermentationEntity);
        VolumeEntity volumeEntity = new VolumeEntity(1, "testUnit");
        BoilVolumeEntity boilVolumeEntity = new BoilVolumeEntity(1, "testUnit");
        BeerEntity beerEntity = new BeerEntity("testname","testDescription", "testUrl", 1.0, 1.0, 1, 1, 1.0, 1.0, 1.0, 1.0,
                volumeEntity, boilVolumeEntity, methodEntity, ingredientsEntity, pairingList, "test-tip", "test author");
        beerRepository.save(beerEntity);
        Long id = beerEntity.getId();
        Optional<BeerEntity> fetchedBeerEntity = beerRepository.findById(id);
        //when
        BeerDto beerDto = mapper.mapToBeerDto(fetchedBeerEntity.get());
        //then
        assertNotNull(beerDto);
        //dunno what to test here-maybe live app would help

    }

    @Test
    void mapToBeerEntity() {
        List<String> pairingList = new ArrayList<>(List.of("pair1", "pair2"));
        AmountEntity amountEntity = new AmountEntity(1.0, "testAmount");
        MaltEntity maltEntity = new MaltEntity("testMalt", amountEntity);
        HopsEntity hopsEntity = new HopsEntity("testName", amountEntity, "testAdd", "testAttribute");
        List<MaltEntity> maltEntityList = new ArrayList<>(List.of(maltEntity));
        List<HopsEntity> hopsEntityList = new ArrayList<>(List.of(hopsEntity));
        IngredientsEntity ingredientsEntity = new IngredientsEntity(maltEntityList, hopsEntityList, "testYeast");
        TempEntity tempEntity = new TempEntity(1, "testTemp");
        MashTempEntity mashTempEntity = new MashTempEntity(tempEntity, 1);
        MashTempEntity mashTempEntity2 = new MashTempEntity(tempEntity, 2);
        List<MashTempEntity> mashTempEntityList = new ArrayList<>(List.of(mashTempEntity, mashTempEntity2));
        FermentationEntity fermentationEntity = new FermentationEntity(new TempEntity(1, "testTemp"));
        MethodEntity methodEntity = new MethodEntity(mashTempEntityList, fermentationEntity);
        VolumeEntity volumeEntity = new VolumeEntity(1, "testUnit");
        BoilVolumeEntity boilVolumeEntity = new BoilVolumeEntity(1, "testUnit");
        BeerEntity beerEntity = new BeerEntity("testname","testDescription", "testUrl", 1.0, 1.0, 1, 1, 1.0, 1.0, 1.0, 1.0,
                volumeEntity, boilVolumeEntity, methodEntity, ingredientsEntity, pairingList, "test-tip", "test author");
        beerRepository.save(beerEntity);
        Long id = beerEntity.getId();
        Optional<BeerEntity> fetchedBeerEntity = beerRepository.findById(id);
        BeerDto beerDto = mapper.mapToBeerDto(fetchedBeerEntity.get());
        //when
        BeerEntity convertedBeerEntity = mapper.mapToBeerEntity(beerDto);
        //then
        assertNotNull(convertedBeerEntity);
        assertEquals(convertedBeerEntity, beerEntity);
    }


    @Test
    void mapMealEntityToMealDto() {
        //given
        MealEntity singleMealEntity = new MealEntity();
        singleMealEntity.setId(123L);
        singleMealEntity.setId(1L);
        singleMealEntity.setName("test name");
        singleMealEntity.setCategory("test category");
        singleMealEntity.setArea("test area");
        singleMealEntity.setInstruction("test instructions");
        singleMealEntity.setThumbnail("test thumbnail");
        singleMealEntity.setTags("test tags");
        singleMealEntity.setYoutubeLink("test youtube");
        singleMealEntity.setSource("test source");
        singleMealEntity.setIngredientsAndMeasureEntityList(new ArrayList<>());
        singleMealEntity.getIngredientsAndMeasureEntityList().add(new IngredientAndMeasureEntity("i1", "m1"));
        singleMealEntity.getIngredientsAndMeasureEntityList().add(new IngredientAndMeasureEntity("i2", "m3"));
        singleMealEntity.getIngredientsAndMeasureEntityList().add(new IngredientAndMeasureEntity("i2", "m3"));
        //when
        MealDto mealEntity = mapper.mapMealEntityToMealDto(singleMealEntity);
        //then
        Assertions.assertEquals(singleMealEntity.getId(), mealEntity.getId());
        Assertions.assertEquals(singleMealEntity.getIngredientsAndMeasureEntityList().size(), mealEntity.getIngredientsAndMeasureDtoList().size());
        Assertions.assertEquals(singleMealEntity.getIngredientsAndMeasureEntityList().get(1).getIngredientName(), mealEntity.getIngredientsAndMeasureDtoList().get(1).getIngredientName());
    }


    @Test
    void mapMealDtoToMealEntity() {
        //given
        MealDto singleMealDto = new MealDto();
        singleMealDto.setId(123L);
        singleMealDto.setId(1L);
        singleMealDto.setName("test name");
        singleMealDto.setCategory("test category");
        singleMealDto.setArea("test area");
        singleMealDto.setInstruction("test instructions");
        singleMealDto.setThumbnail("test thumbnail");
        singleMealDto.setTags("test tags");
        singleMealDto.setYoutubeLink("test youtube");
        singleMealDto.setSource("test source");
        singleMealDto.setIngredientsAndMeasureDtoList(new ArrayList<>());
        singleMealDto.getIngredientsAndMeasureDtoList().add(new IngredientAndMeasureDto("i1", "m1"));
        singleMealDto.getIngredientsAndMeasureDtoList().add(new IngredientAndMeasureDto("i2", "m2"));
        singleMealDto.getIngredientsAndMeasureDtoList().add(new IngredientAndMeasureDto("i3", "m3"));
        //when
        MealEntity mealEntity = mapper.mapFromMealDtoToMealEntity(singleMealDto);
        //then
        Assertions.assertEquals(singleMealDto.getId(), mealEntity.getId());
        Assertions.assertEquals(singleMealDto.getIngredientsAndMeasureDtoList().size(), mealEntity.getIngredientsAndMeasureEntityList().size());
        Assertions.assertEquals(singleMealDto.getIngredientsAndMeasureDtoList().get(1).getIngredientName(), mealEntity.getIngredientsAndMeasureEntityList().get(1).getIngredientName());
    }

    @Test
    void mapToMealApiDtoToDto() {
        //given
        SingleMealApiDto singleMealApiDto = new SingleMealApiDto();
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
        MealDto mealDto = mapper.mapFromApiDtoToMealDto(singleMealApiDto);
        //then
        Assertions.assertEquals(3, mealDto.getIngredientsAndMeasureDtoList().size());
        Assertions.assertEquals(singleMealApiDto.getId(), mealDto.getId());
        Assertions.assertEquals(singleMealApiDto.getIngredient2(), mealDto.getIngredientsAndMeasureDtoList().get(1).getIngredientName());
    }
}
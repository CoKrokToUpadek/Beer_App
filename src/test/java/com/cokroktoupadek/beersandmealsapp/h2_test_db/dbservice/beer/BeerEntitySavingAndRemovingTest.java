package com.cokroktoupadek.beersandmealsapp.h2_test_db.dbservice.beer;

import com.cokroktoupadek.beersandmealsapp.domain.entity.beer.*;
import com.cokroktoupadek.beersandmealsapp.mapper.BeerEntityFilterAndSaver;
import com.cokroktoupadek.beersandmealsapp.service.beer.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
@TestPropertySource("classpath:application-H2TestDb.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BeerEntitySavingAndRemovingTest {
//tests in this class are integral with each other
    @Autowired
    BeerEntityManipulatorDbService beerEntityManipulatorDbService;
    @Autowired
    VolumeDbService volumeDbService;
    @Autowired
    BoilVolumeDbService boilVolumeDbService;
    @Autowired
    TempDbService tempDbService;
    @Autowired
    AmountDbService amountDbService;
    @Autowired
    BeerDbService beerDbService;

    @Autowired
    BeerEntityFilterAndSaver beerEntityFilterAndSaver;



    @Test
    @Transactional
    @Rollback(value = false)
    @Order(1)
    void beerSavingProcedure(){
        //given
        List<String> pairingList = new ArrayList<>(List.of("pair1", "pair2"));
        AmountEntity amountEntity = new AmountEntity(1.0, "testAmount");
        MaltEntity maltEntity = new MaltEntity("testMalt", amountEntity);
        HopsEntity hopsEntity = new HopsEntity("testName", amountEntity, "testAdd", "testAttribute");
        List<MaltEntity> maltEntityList = new ArrayList<>(List.of(maltEntity));
        List<HopsEntity> hopsEntityList = new ArrayList<>(List.of(hopsEntity));
        IngredientsEntity ingredientsEntity = new IngredientsEntity(maltEntityList, hopsEntityList, "testYeast");
        TempEntity tempEntity = new TempEntity(1, "testTemp");
        MashTempEntity mashTempEntity = new MashTempEntity(tempEntity, 1);
        List<MashTempEntity> mashTempEntityList = new ArrayList<>(List.of(mashTempEntity));
        FermentationEntity fermentationEntity = new FermentationEntity(tempEntity);
        MethodEntity methodEntity = new MethodEntity(mashTempEntityList, fermentationEntity);
        VolumeEntity volumeEntity = new VolumeEntity(1, "testUnit");
        BoilVolumeEntity boilVolumeEntity = new BoilVolumeEntity(1, "testUnit");
        BeerEntity beerEntity0 = new BeerEntity("testName0", "testDescription", "testUrl", 1.0, 1.0, 1, 1, 1.0, 1.0, 1.0, 1.0,
         volumeEntity, boilVolumeEntity, methodEntity, ingredientsEntity, pairingList, "test-tip", "test author");
        //when
        beerEntityFilterAndSaver.beerEntitySaver(beerEntity0);
        //then
        Assertions.assertTrue(beerDbService.findByName("testName0").isPresent());
    }
    //data setup that can be done within junit (it just saves the data with new transaction so the mapped by refreneces are created)
    @Test
    @Transactional
    @Rollback(value = false)
    @Order(2)
    void setupTestData(){
        List<String> pairingList = new ArrayList<>(List.of("pair1", "pair2"));
        AmountEntity amountEntity = new AmountEntity(1.0, "testAmount");
        MaltEntity maltEntity = new MaltEntity("testMalt", amountEntity);
        HopsEntity hopsEntity = new HopsEntity("testName", amountEntity, "testAdd", "testAttribute");
        List<MaltEntity> maltEntityList = new ArrayList<>(List.of(maltEntity));
        List<HopsEntity> hopsEntityList = new ArrayList<>(List.of(hopsEntity));
        IngredientsEntity ingredientsEntity = new IngredientsEntity(maltEntityList, hopsEntityList, "testYeast");
        TempEntity tempEntity = new TempEntity(1, "testTemp");
        MashTempEntity mashTempEntity = new MashTempEntity(tempEntity, 1);
        List<MashTempEntity> mashTempEntityList = new ArrayList<>(List.of(mashTempEntity));
        FermentationEntity fermentationEntity = new FermentationEntity(tempEntity);
        MethodEntity methodEntity = new MethodEntity(mashTempEntityList, fermentationEntity);
        VolumeEntity volumeEntity = new VolumeEntity(1, "testUnit");
        BoilVolumeEntity boilVolumeEntity = new BoilVolumeEntity(1, "testUnit");;
        BeerEntity beerEntity1 = new BeerEntity("testName1", "testDescription", "testUrl", 1.0, 1.0, 1, 1, 1.0, 1.0, 1.0, 1.0,
                volumeEntity, boilVolumeEntity, methodEntity, ingredientsEntity, pairingList, "test-tip", "test author");
        BeerEntity beerEntity2 = new BeerEntity("testName2", "testDescription", "testUrl", 1.0, 1.0, 1, 1, 1.0, 1.0, 1.0, 1.0,
                volumeEntity, boilVolumeEntity, methodEntity, ingredientsEntity, pairingList, "test-tip", "test author");

        beerEntityFilterAndSaver.beerEntitySaver(beerEntity1);
        beerEntityFilterAndSaver.beerEntitySaver(beerEntity2);
    }

    @Test
    @Transactional
    @Rollback(value = false)
    @Order(3)
    void beerEntityDeleter() {
        //given
        //when
        beerEntityManipulatorDbService.beerEntityDeleter("testName0");
        beerEntityManipulatorDbService.entitiesWithEmptyRelationsCleaner();
        //then
        Assertions.assertTrue(beerDbService.findByName("testName0").isEmpty());
        Assertions.assertTrue(tempDbService.findByValueAndUnit(1,"testTemp").isPresent());
        Assertions.assertTrue(amountDbService.findByValueAndUnit(1.0,"testAmount").isPresent());
        Assertions.assertTrue(volumeDbService.findByUnitAndValue("testUnit",1).isPresent());
        Assertions.assertTrue(boilVolumeDbService.findByUnitAndValue("testUnit",1).isPresent());
    }

    @Test
    @Transactional
    @Rollback(value = false)
    @Order(4)
    void allBeerEntitiesDeleter() {
        //given
        //when
        List<String> entities=beerDbService.findAll().stream().map(BeerEntity::getName).collect(Collectors.toList());
        beerEntityManipulatorDbService.allBeerEntitiesDeleter(entities);
        beerEntityManipulatorDbService.entitiesWithEmptyRelationsCleaner();
        //then
        Assertions.assertTrue(beerDbService.findAll().isEmpty());
        Assertions.assertTrue(tempDbService.findByValueAndUnit(1,"testTemp").isEmpty());
        Assertions.assertTrue(amountDbService.findByValueAndUnit(1.0,"testAmount").isEmpty());
        Assertions.assertTrue(volumeDbService.findByUnitAndValue("testUnit",1).isEmpty());
        Assertions.assertTrue(boilVolumeDbService.findByUnitAndValue("testUnit",1).isEmpty());
    }

}

//package com.cokroktoupadek.beersandmealsapp.mysql_live_db;
//
//import com.cokroktoupadek.beersandmealsapp.domain.entity.beer.*;
//import com.cokroktoupadek.beersandmealsapp.mapper.BeerEntityFilterAndSaver;
//import com.cokroktoupadek.beersandmealsapp.service.beer.*;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.test.context.TestPropertySource;
//
//import javax.transaction.Transactional;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//
//@SpringBootTest
//@TestPropertySource("classpath:application-LiveDb.properties")
//@Rollback(value = false)
//public class TestForLiveDb {
//
//    @Autowired
//    BeerEntityManipulatorDbService beerEntityManipulatorDbService;
//    @Autowired
//    VolumeDbService volumeDbService;
//    @Autowired
//    BoilVolumeDbService boilVolumeDbService;
//    @Autowired
//    TempDbService tempDbService;
//    @Autowired
//    AmountDbService amountDbService;
//    @Autowired
//    BeerDbService beerDbService;
//
//    @Autowired
//    BeerEntityFilterAndSaver beerEntityFilterAndSaver;
//
//    @Test
//    @Transactional
//    @Rollback(value = false)
//    void datasaver(){
//        //given
//        List<String> pairingList = new ArrayList<>(List.of("pair1", "pair2"));
//        AmountEntity amountEntity = new AmountEntity(1.0, "testAmount");
//        MaltEntity maltEntity = new MaltEntity("testMalt", amountEntity);
//        HopsEntity hopsEntity = new HopsEntity("testName", amountEntity, "testAdd", "testAttribute");
//        List<MaltEntity> maltEntityList = new ArrayList<>(List.of(maltEntity));
//        List<HopsEntity> hopsEntityList = new ArrayList<>(List.of(hopsEntity));
//        IngredientsEntity ingredientsEntity = new IngredientsEntity(maltEntityList, hopsEntityList, "testYeast");
//        TempEntity tempEntity = new TempEntity(1, "testTemp");
//        MashTempEntity mashTempEntity = new MashTempEntity(tempEntity, 1);
//        List<MashTempEntity> mashTempEntityList = new ArrayList<>(List.of(mashTempEntity));
//        FermentationEntity fermentationEntity = new FermentationEntity(tempEntity);
//        MethodEntity methodEntity = new MethodEntity(mashTempEntityList, fermentationEntity);
//        VolumeEntity volumeEntity = new VolumeEntity(1, "testUnit");
//        BoilVolumeEntity boilVolumeEntity = new BoilVolumeEntity(1, "testUnit");;
//        BeerEntity beerEntity0 = new BeerEntity("testName0", "testDescription", "testUrl", 1.0, 1.0, 1, 1, 1.0, 1.0, 1.0, 1.0,
//                volumeEntity, boilVolumeEntity, methodEntity, ingredientsEntity, pairingList, "test-tip", "test author");
//        BeerEntity beerEntity1 = new BeerEntity("testName1", "testDescription", "testUrl", 1.0, 1.0, 1, 1, 1.0, 1.0, 1.0, 1.0,
//                volumeEntity, boilVolumeEntity, methodEntity, ingredientsEntity, pairingList, "test-tip", "test author");
//
//
//        beerEntityFilterAndSaver.beerEntitySaver(beerEntity0);
//        beerEntityFilterAndSaver.beerEntitySaver(beerEntity1);
//
//    }
//
//
//    @Test
//    @Transactional
//    @Rollback(value = false)
//    void beerEntityDeleter() {
//
//
//        List<BeerEntity> beerEntityList= beerDbService.findAll();
//
//        Optional<BeerEntity> fetchedBeerEntity=beerDbService.findByName("testName0");
//        //when
//        beerEntityManipulatorDbService.beerEntityDeleter(fetchedBeerEntity.get().getName());
//        //then
//        Assertions.assertTrue(beerDbService.findByName("testName0").isEmpty());
//        Assertions.assertTrue(tempDbService.findByValueAndUnit(1,"testTemp").isPresent());
//        Assertions.assertTrue(amountDbService.findByValueAndUnit(1.0,"testAmount").isPresent());
//        Assertions.assertTrue(volumeDbService.findByUnitAndValue("testUnit",1).isPresent());
//        Assertions.assertTrue(boilVolumeDbService.findByUnitAndValue("testUnit",1).isPresent());
//    }
//}

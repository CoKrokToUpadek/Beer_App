package com.cokroktoupadek.beersandmealsapp.service.beer;

import com.cokroktoupadek.beersandmealsapp.domain.entity.beer.*;
import com.cokroktoupadek.beersandmealsapp.mapper.BeerEntityFilterAndSaver;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



@Transactional
@SpringBootTest
@TestPropertySource("classpath:application-H2TestDb.properties")
class BeerEntityManipulatorAndFilterTest {

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

    @AfterEach
    void deleteAll(){
        volumeDbService.deleteAll();
        boilVolumeDbService.deleteAll();
        tempDbService.deleteAll();
        amountDbService.deleteAll();
        beerDbService.deleteAll();
    }

    @Test
    void amountDuplicateVerifierEntityPresent() {
        //given
        AmountEntity amountEntity=new AmountEntity(1d,"unit");
        amountDbService.save(amountEntity);
        //when
        AmountEntity fetchedAmount=beerEntityManipulatorDbService.amountDuplicateVerifier(amountEntity);
        //then
        Assertions.assertEquals(amountEntity,fetchedAmount);
    }

    @Test
    void amountDuplicateVerifierEntityNotPresent() {
        //given
        AmountEntity amountEntity=new AmountEntity(1d,"unit");
        //when
        AmountEntity fetchedAmount=beerEntityManipulatorDbService.amountDuplicateVerifier(amountEntity);
        //then
        Assertions.assertEquals(fetchedAmount,amountDbService.findByValueAndUnit(1d,"unit").get());

    }


    @Test
    void boilVolumeDuplicateVerifierEntityPresent() {
        //given
        BoilVolumeEntity boilVolumeEntity=new BoilVolumeEntity(1,"unit");
        boilVolumeDbService.save(boilVolumeEntity);
        //when
        BoilVolumeEntity fetchedBoilVolume=beerEntityManipulatorDbService.boilVolumeDuplicateVerifier(boilVolumeEntity);
        //then
        Assertions.assertEquals(boilVolumeEntity,fetchedBoilVolume);
    }

    @Test
    void boilVolumeDuplicateVerifierEntityNotPresent() {
        //given
        BoilVolumeEntity boilVolumeEntity=new BoilVolumeEntity(1,"unit");
        //when
        BoilVolumeEntity fetchedBoilVolume=beerEntityManipulatorDbService.boilVolumeDuplicateVerifier(boilVolumeEntity);
        //then
        Assertions.assertEquals(fetchedBoilVolume,boilVolumeDbService.findByUnitAndValue("unit",1).get());
    }

    @Test
    void tempDuplicateVerifierEntityPresent() {
        //given
        TempEntity tempEntity=new TempEntity(1,"unit");
        tempDbService.save(tempEntity);
        //when
        TempEntity fetchedTempEntity=beerEntityManipulatorDbService.tempDuplicateVerifier(tempEntity);
        //then
        Assertions.assertEquals(tempEntity,fetchedTempEntity);
    }

    @Test
    void tempDuplicateVerifierEntityNotPresent() {
        //given
        TempEntity tempEntity=new TempEntity(1,"unit");
        //when
        TempEntity fetchedTempEntity=beerEntityManipulatorDbService.tempDuplicateVerifier(tempEntity);
        //then
        Assertions.assertEquals(fetchedTempEntity,tempDbService.findByValueAndUnit(1,"unit").get());
    }

    @Test
    void volumeDuplicateVerifierEntityPresent() {
        //given
        VolumeEntity volumeEntity=new VolumeEntity(1,"unit");
        volumeDbService.save(volumeEntity);
        //when
        VolumeEntity fetchedVolume=beerEntityManipulatorDbService.volumeDuplicateVerifier(volumeEntity);
        //then
        Assertions.assertEquals(volumeEntity,fetchedVolume);
    }

    @Test
    void beerExistenceInDbVerifierEntityNotPresent() {
        //given
        VolumeEntity volumeEntity=new VolumeEntity(1,"unit");
        //when
        VolumeEntity fetchedVolume=beerEntityManipulatorDbService.volumeDuplicateVerifier(volumeEntity);
        //then
        Assertions.assertEquals(fetchedVolume,volumeDbService.findByUnitAndValue("unit",1).get());
    }

    @Test
    void beerSavingProcedure(){
        //given
        List<String> pairingList = new ArrayList<>(List.of("pair1", "pair2"));
        AmountEntity amountEntity = new AmountEntity(1.0, "testAmount");
        amountDbService.save(amountEntity);
        MaltEntity maltEntity = new MaltEntity("testMalt", amountEntity);
        HopsEntity hopsEntity = new HopsEntity("testName", amountEntity, "testAdd", "testAttribute");
        List<MaltEntity> maltEntityList = new ArrayList<>(List.of(maltEntity));
        List<HopsEntity> hopsEntityList = new ArrayList<>(List.of(hopsEntity));
        IngredientsEntity ingredientsEntity = new IngredientsEntity(maltEntityList, hopsEntityList, "testYeast");
        TempEntity tempEntity = new TempEntity(1, "testTemp");
        tempDbService.save(tempEntity);
        MashTempEntity mashTempEntity = new MashTempEntity(tempEntity, 1);
        List<MashTempEntity> mashTempEntityList = new ArrayList<>(List.of(mashTempEntity));
        FermentationEntity fermentationEntity = new FermentationEntity(tempEntity);
        MethodEntity methodEntity = new MethodEntity(mashTempEntityList, fermentationEntity);
        VolumeEntity volumeEntity = new VolumeEntity(1, "testUnit");
        volumeDbService.save(volumeEntity);
        BoilVolumeEntity boilVolumeEntity = new BoilVolumeEntity(1, "testUnit");
        boilVolumeDbService.save(boilVolumeEntity);
        BeerEntity beerEntity0 = new BeerEntity("testName1", "testDescription", "testUrl", 1.0, 1.0, 1, 1, 1.0, 1.0, 1.0, 1.0,
                volumeEntity, boilVolumeEntity, methodEntity, ingredientsEntity, pairingList, "test-tip", "test author");
        //when
        beerEntityFilterAndSaver.beerEntitySaver(beerEntity0);
        //then
        Assertions.assertTrue(beerDbService.findByName("testName1").isPresent());

    }



    @Test
    void beerEntityDeleter() {
        //given
        List<String> pairingList = new ArrayList<>(List.of("pair1", "pair2"));
        AmountEntity amountEntity = new AmountEntity(1.0, "testAmount");
        amountDbService.save(amountEntity);
        MaltEntity maltEntity = new MaltEntity("testMalt", amountEntity);
        HopsEntity hopsEntity = new HopsEntity("testName", amountEntity, "testAdd", "testAttribute");
        List<MaltEntity> maltEntityList = new ArrayList<>(List.of(maltEntity));
        List<HopsEntity> hopsEntityList = new ArrayList<>(List.of(hopsEntity));
        IngredientsEntity ingredientsEntity = new IngredientsEntity(maltEntityList, hopsEntityList, "testYeast");
        TempEntity tempEntity = new TempEntity(1, "testTemp");
        tempDbService.save(tempEntity);
        MashTempEntity mashTempEntity = new MashTempEntity(tempEntity, 1);
        List<MashTempEntity> mashTempEntityList = new ArrayList<>(List.of(mashTempEntity));
        FermentationEntity fermentationEntity = new FermentationEntity(tempEntity);
        MethodEntity methodEntity = new MethodEntity(mashTempEntityList, fermentationEntity);
        VolumeEntity volumeEntity = new VolumeEntity(1, "testUnit");
        volumeDbService.save(volumeEntity);
        BoilVolumeEntity boilVolumeEntity = new BoilVolumeEntity(1, "testUnit");
        boilVolumeDbService.save(boilVolumeEntity);
        BeerEntity beerEntity0 = new BeerEntity("testName0", "testDescription", "testUrl", 1.0, 1.0, 1, 1, 1.0, 1.0, 1.0, 1.0,
                volumeEntity, boilVolumeEntity, methodEntity, ingredientsEntity, pairingList, "test-tip", "test author");
        BeerEntity beerEntity1 = new BeerEntity("testName1", "testDescription", "testUrl", 1.0, 1.0, 1, 1, 1.0, 1.0, 1.0, 1.0,
                volumeEntity, boilVolumeEntity, methodEntity, ingredientsEntity, pairingList, "test-tip", "test author");

        beerEntityFilterAndSaver.beerEntitySaver(beerEntity0);
        beerEntityFilterAndSaver.beerEntitySaver(beerEntity1);

        Optional<BeerEntity> fetchedBeerEntity=beerDbService.findByName("testName0");
        //when
        beerEntityManipulatorDbService.beerEntityDeleter(fetchedBeerEntity.get().getName());
        //then
        Assertions.assertTrue(beerDbService.findByName(beerEntity0.getName()).isEmpty());
        Assertions.assertTrue(tempDbService.findByValueAndUnit(1,"testTemp").isPresent());
        Assertions.assertTrue(amountDbService.findByValueAndUnit(1.0,"testAmount").isPresent());
        Assertions.assertTrue(volumeDbService.findByUnitAndValue("testUnit",1).isPresent());
        Assertions.assertTrue(boilVolumeDbService.findByUnitAndValue("testUnit",1).isPresent());
    }

//    @Test
//    void allBeerEntitiesDeleter() {
//        //given
//        List<String> pairingList = new ArrayList<>(List.of("pair1", "pair2"));
//        AmountEntity amountEntity = new AmountEntity(1.0, "testAmount");
//        amountDbService.save(amountEntity);
//        MaltEntity maltEntity = new MaltEntity("testMalt", amountEntity);
//        HopsEntity hopsEntity = new HopsEntity("testName", amountEntity, "testAdd", "testAttribute");
//        List<MaltEntity> maltEntityList = new ArrayList<>(List.of(maltEntity));
//        List<HopsEntity> hopsEntityList = new ArrayList<>(List.of(hopsEntity));
//        IngredientsEntity ingredientsEntity = new IngredientsEntity(maltEntityList, hopsEntityList, "testYeast");
//        TempEntity tempEntity = new TempEntity(1, "testTemp");
//        tempDbService.save(tempEntity);
//        MashTempEntity mashTempEntity = new MashTempEntity(tempEntity, 1);
//        List<MashTempEntity> mashTempEntityList = new ArrayList<>(List.of(mashTempEntity));
//        FermentationEntity fermentationEntity = new FermentationEntity(tempEntity);
//        MethodEntity methodEntity = new MethodEntity(mashTempEntityList, fermentationEntity);
//        VolumeEntity volumeEntity = new VolumeEntity(1, "testUnit");
//        volumeDbService.save(volumeEntity);
//        BoilVolumeEntity boilVolumeEntity = new BoilVolumeEntity(1, "testUnit");
//        boilVolumeDbService.save(boilVolumeEntity);
//        BeerEntity beerEntity0 = new BeerEntity("testName1", "testDescription", "testUrl", 1.0, 1.0, 1, 1, 1.0, 1.0, 1.0, 1.0,
//                volumeEntity, boilVolumeEntity, methodEntity, ingredientsEntity, pairingList, "test-tip", "test author");
//        BeerEntity beerEntity1 = new BeerEntity("testName2", "testDescription", "testUrl", 1.0, 1.0, 1, 1, 1.0, 1.0, 1.0, 1.0,
//                volumeEntity, boilVolumeEntity, methodEntity, ingredientsEntity, pairingList, "test-tip", "test author");
//        beerDbService.save(beerEntity0);
//        beerDbService.save(beerEntity1);
//        //when
//        beerEntityManipulatorDbService.allBeerEntitiesDeleter(beerDbService.findAll().stream().map(BeerEntity::getName).collect(Collectors.toList()));
//        //then
//        Assertions.assertTrue(beerDbService.findByName(beerEntity0.getName()).isEmpty());
//        Assertions.assertTrue(tempDbService.findByValueAndUnit(1,"testTemp").isEmpty());
//        Assertions.assertTrue(amountDbService.findByValueAndUnit(1.0,"testAmount").isEmpty());
//        Assertions.assertTrue(volumeDbService.findByUnitAndValue("testUnit",1).isEmpty());
//        Assertions.assertTrue(boilVolumeDbService.findByUnitAndValue("testUnit",1).isEmpty());
//    }

    @Test
    void entitiesWithEmptyRelationsCleaner() {
        //given
        //when
        //then
    }
}
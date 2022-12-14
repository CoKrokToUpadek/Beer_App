package com.cokroktoupadek.beersandmealsapp.h2_test_db.dbservice.beer;

import com.cokroktoupadek.beersandmealsapp.domain.entity.beer.*;
import com.cokroktoupadek.beersandmealsapp.service.beer.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@TestPropertySource("classpath:application-H2TestDb.properties")
class BeerDServiceTest {
    @Autowired
    BeerDbService beerDbService;

    @Autowired
    TempDbService tempDbService;

    @Autowired
    AmountDbService amountDbService;

    @Autowired
    VolumeDbService volumeDbService;

    @Autowired
    BoilVolumeDbService boilVolumeDbService;


    @Test
    void addBeerTest() {
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
        BeerEntity beerEntity = new BeerEntity("testName", "testDescription", "testUrl", 1.0, 1.0, 1, 1, 1.0, 1.0, 1.0, 1.0,
                volumeEntity, boilVolumeEntity, methodEntity, ingredientsEntity, pairingList, "test-tip", "test author");
        //when
        beerDbService.save(beerEntity);
        //then
        Long id = beerEntity.getId();
        Optional<BeerEntity> fetchedBeerEntity = beerDbService.findById(id);
        if (fetchedBeerEntity.isEmpty()) {
            Assertions.fail("fetched value is empty");
        } else {
            Assertions.assertEquals(beerEntity, fetchedBeerEntity.get());
        }
        //cleanup
        beerDbService.deleteAll();
        tempDbService.deleteAll();
        amountDbService.deleteAll();
        volumeDbService.deleteAll();
        boilVolumeDbService.deleteAll();
    }

    @Test
    void findBeerByNameTest() {
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
        BeerEntity beerEntity = new BeerEntity("testName", "testDescription", "testUrl", 1.0, 1.0, 1, 1, 1.0, 1.0, 1.0, 1.0,
                volumeEntity, boilVolumeEntity, methodEntity, ingredientsEntity, pairingList, "test-tip", "test author");
        //when
        beerDbService.save(beerEntity);
        //then
        Optional<BeerEntity> fetchedBeerEntity = beerDbService.findByName("testName");
        if (fetchedBeerEntity.isEmpty()) {
            Assertions.fail("fetched value is empty");
        } else {
            Assertions.assertEquals(beerEntity, fetchedBeerEntity.get());
        }
        //cleanup
        beerDbService.deleteAll();
        tempDbService.deleteAll();
        amountDbService.deleteAll();
        volumeDbService.deleteAll();
        boilVolumeDbService.deleteAll();

    }

}
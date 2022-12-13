package com.cokroktoupadek.beersandmealsapp.h2_test_db.dbservice.beer;

import com.cokroktoupadek.beersandmealsapp.domain.entity.beer.BoilVolumeEntity;
import com.cokroktoupadek.beersandmealsapp.service.beer.BoilVolumeDbService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

@SpringBootTest
@TestPropertySource("classpath:application-H2TestDb.properties")
class BoilVolumeDbServiceTest {
    @Autowired
    BoilVolumeDbService boilVolumeDbService;

    @Test
    void addBoilVolumeTest(){
        //given
        BoilVolumeEntity boilVolumeEntity=new BoilVolumeEntity(1,"testBoiledVolume");
        //when
        boilVolumeDbService.save(boilVolumeEntity);
        //then
        Long id=boilVolumeEntity.getId();
        Optional<BoilVolumeEntity> fetchedBoilVolumeEntity= boilVolumeDbService.findById(id);
        if (fetchedBoilVolumeEntity.isEmpty()){
            Assertions.fail("fetched value is empty");
        }else {
            Assertions.assertEquals(boilVolumeEntity, fetchedBoilVolumeEntity.get());
        }
        //cleanup
        boilVolumeDbService.deleteAll();
    }


    @Test
    void findBoilVolumeByUnitAndValueTest(){
        //given
        BoilVolumeEntity boilVolumeEntity=new BoilVolumeEntity(1,"testBoiledVolume");
        //when
        boilVolumeDbService.save(boilVolumeEntity);
        //then
        Optional<BoilVolumeEntity> fetchedBoilVolumeEntity= boilVolumeDbService.findByUnitAndValue("testBoiledVolume",1);
        if (fetchedBoilVolumeEntity.isEmpty()){
            Assertions.fail("fetched value is empty");
        }else {
            Assertions.assertEquals(boilVolumeEntity, fetchedBoilVolumeEntity.get());
        }
        //cleanup
        boilVolumeDbService.deleteAll();
    }

}
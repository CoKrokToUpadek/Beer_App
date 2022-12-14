package com.cokroktoupadek.beersandmealsapp.h2_test_db.dbservice.beer;

import com.cokroktoupadek.beersandmealsapp.domain.entity.beer.VolumeEntity;
import com.cokroktoupadek.beersandmealsapp.service.beer.VolumeDbService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import javax.transaction.Transactional;
import java.util.Optional;
@Transactional
@SpringBootTest
@TestPropertySource("classpath:application-H2TestDb.properties")
class VolumeDbServiceTest {
    @Autowired
    VolumeDbService volumeDbService;

    @Test
    void findByUnitAndValueTest(){
        //given
        VolumeEntity volumeEntity=new VolumeEntity(1,"testUnit");
        //when
        volumeDbService.save(volumeEntity);
        //then
        Optional<VolumeEntity> fetchedVolumeEntity= volumeDbService.findByUnitAndValue("testUnit",1);
        if (fetchedVolumeEntity.isEmpty()){
            Assertions.fail("fetched value is empty");
        }else {
            Assertions.assertEquals(volumeEntity,fetchedVolumeEntity.get());
        }
        //cleanup
        volumeDbService.deleteAll();
    }

}
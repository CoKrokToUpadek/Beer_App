package com.cokroktoupadek.beersandmealsbackend.h2_test_db.service.beer;

import com.cokroktoupadek.beersandmealsbackend.domain.entity.beer.MashTempEntity;
import com.cokroktoupadek.beersandmealsbackend.domain.entity.beer.TempEntity;
import com.cokroktoupadek.beersandmealsbackend.service.beer.MashTempDbService;
import com.cokroktoupadek.beersandmealsbackend.service.beer.TempDbService;
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
class MashTempDbServiceTest {
    @Autowired
    MashTempDbService mashTempDbService;

    @Autowired
    TempDbService tempDbService;
    @Test
    void addMashTempTest(){
        //given
        TempEntity tempEntity=new TempEntity(1,"testTemp");
        tempDbService.save(tempEntity);
        MashTempEntity mashTempEntity =new MashTempEntity(tempEntity,1);
        //when
        mashTempDbService.save(mashTempEntity);
        //then
        Long id= mashTempEntity.getId();
        Optional<MashTempEntity> fetchedMashTempEntity = mashTempDbService.findById(id);
        if (fetchedMashTempEntity.isEmpty()){
            Assertions.fail("fetched value is empty");
        }else {
            Assertions.assertEquals(mashTempEntity, fetchedMashTempEntity.get());
        }
        //cleanup
        mashTempDbService.deleteAll();
        tempDbService.deleteAll();
    }
}
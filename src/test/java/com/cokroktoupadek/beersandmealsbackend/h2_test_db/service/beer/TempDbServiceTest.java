package com.cokroktoupadek.beersandmealsbackend.h2_test_db.service.beer;

import com.cokroktoupadek.beersandmealsbackend.domain.entity.beer.TempEntity;
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
class TempDbServiceTest {
    @Autowired
    TempDbService tempDbService;

    @Test
    void addTempTest(){
        //given
        TempEntity tempEntity=new TempEntity(1,"testTemp");
        //when
        tempDbService.save(tempEntity);
        //then
        Optional<TempEntity>fetchedTempEntity= tempDbService.findByValueAndUnit(1,"testTemp");
        if (fetchedTempEntity.isEmpty()){
            Assertions.fail("fetched value is empty");
        }else {
            Assertions.assertEquals(tempEntity,fetchedTempEntity.get());
        }
        //cleanup
        tempDbService.deleteAll();
    }

}
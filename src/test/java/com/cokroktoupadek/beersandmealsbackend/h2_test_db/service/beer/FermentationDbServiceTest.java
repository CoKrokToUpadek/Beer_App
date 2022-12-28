package com.cokroktoupadek.beersandmealsbackend.h2_test_db.service.beer;

import com.cokroktoupadek.beersandmealsbackend.domain.entity.beer.FermentationEntity;
import com.cokroktoupadek.beersandmealsbackend.domain.entity.beer.TempEntity;
import com.cokroktoupadek.beersandmealsbackend.service.beer.FermentationDbService;
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
class FermentationDbServiceTest {

    @Autowired
    FermentationDbService fermentationDbService;
    @Autowired
    TempDbService tempDbService;



    @Test
    void addFermentationTest(){
        //given
        TempEntity tempEntity=new TempEntity(1,"testTemp");
        tempDbService.save(tempEntity);
        FermentationEntity fermentationEntity =new FermentationEntity(tempEntity);
        //when
        fermentationDbService.save(fermentationEntity);
        //then
        Long id=fermentationEntity.getId();
        Optional<FermentationEntity> fetchedFermentationEntity = fermentationDbService.findById(id);
        if (fetchedFermentationEntity.isEmpty()){
            Assertions.fail("fetched value is empty");
        }else {
            Assertions.assertEquals(fermentationEntity, fetchedFermentationEntity.get());
        }
        //cleanup
        fermentationDbService.deleteAll();
        tempDbService.deleteAll();
    }

}


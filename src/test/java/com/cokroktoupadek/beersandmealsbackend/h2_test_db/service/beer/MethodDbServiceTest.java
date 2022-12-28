package com.cokroktoupadek.beersandmealsbackend.h2_test_db.service.beer;

import com.cokroktoupadek.beersandmealsbackend.domain.entity.beer.*;
import com.cokroktoupadek.beersandmealsbackend.service.beer.MethodDbService;
import com.cokroktoupadek.beersandmealsbackend.service.beer.TempDbService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Transactional
@SpringBootTest
@TestPropertySource("classpath:application-H2TestDb.properties")
class MethodDbServiceTest {
    @Autowired
    MethodDbService methodDbService;
    @Autowired
    TempDbService tempDbService;


    @Test
    void addMethodTest(){
        //given
        TempEntity tempEntity=new TempEntity(1,"testTemp");
        tempDbService.save(tempEntity);
        MashTempEntity mashTempEntity =new MashTempEntity(tempEntity,1);
        List<MashTempEntity> mashTempEntityList=new ArrayList<>(List.of(mashTempEntity));
        FermentationEntity fermentationEntity=new FermentationEntity(tempEntity);
        MethodEntity methodEntity =new MethodEntity(mashTempEntityList,fermentationEntity);
        //when
        methodDbService.save(methodEntity);
        //then
        Long id= methodEntity.getId();
        Optional<MethodEntity> fetchedMethodEntity = methodDbService.findById(id);

        if (fetchedMethodEntity.isEmpty()){
            Assertions.fail("fetched value is empty");
        }else {
            Assertions.assertEquals(methodEntity,fetchedMethodEntity.get());
        }
        //cleanup
        methodDbService.deleteAll();
        tempDbService.deleteAll();

    }
}
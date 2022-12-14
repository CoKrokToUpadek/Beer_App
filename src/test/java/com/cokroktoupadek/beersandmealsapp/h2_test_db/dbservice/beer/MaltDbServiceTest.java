package com.cokroktoupadek.beersandmealsapp.h2_test_db.dbservice.beer;

import com.cokroktoupadek.beersandmealsapp.domain.entity.beer.AmountEntity;
import com.cokroktoupadek.beersandmealsapp.domain.entity.beer.MaltEntity;
import com.cokroktoupadek.beersandmealsapp.service.beer.AmountDbService;
import com.cokroktoupadek.beersandmealsapp.service.beer.MaltDbService;
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
class MaltDbServiceTest {

    @Autowired
    MaltDbService maltDbService;

    @Autowired
    AmountDbService amountDbService;



    @Test
    void addMaltTest(){
        //given
        AmountEntity amountEntity=new AmountEntity(1.0,"testAmount");
        amountDbService.save(amountEntity);
        MaltEntity maltEntity=new MaltEntity("testMalt",amountEntity);
        //when
        maltDbService.save(maltEntity);
        //then
        Long id=maltEntity.getId();
        Optional<MaltEntity> fetchedMaltEntity= maltDbService.findById(id);
        if (fetchedMaltEntity.isEmpty()){
            Assertions.fail("fetched value is empty");
        }else {
            Assertions.assertEquals(maltEntity, fetchedMaltEntity.get());
        }
        //cleanup
        maltDbService.deleteAll();
    }

}
package com.cokroktoupadek.beersandmealsapp.h2_test_db.dbservice.beer;

import com.cokroktoupadek.beersandmealsapp.domain.entity.beer.AmountEntity;
import com.cokroktoupadek.beersandmealsapp.domain.entity.beer.HopsEntity;
import com.cokroktoupadek.beersandmealsapp.service.beer.AmountDbService;
import com.cokroktoupadek.beersandmealsapp.service.beer.HopsDbService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

@SpringBootTest
@TestPropertySource("classpath:application-H2TestDb.properties")
public class HopsDbServiceTest {
    @Autowired
    HopsDbService hopsDbService;

    @Autowired
    AmountDbService amountDbService;



    @Test
    void addHopsTest(){
        //given
        AmountEntity amountEntity=new AmountEntity(2.0,"testAmount");
        amountDbService.save(amountEntity);
        HopsEntity hopsEntity =new HopsEntity("testName",amountEntity,"testAdd","testAttribute");
        //when
        hopsDbService.save(hopsEntity);
        //then
        Long id= hopsEntity.getId();
        Optional<HopsEntity> fetchedHopsEntity = hopsDbService.findById(id);
        if (fetchedHopsEntity.isEmpty()){
            Assertions.fail("fetched value is empty");
        }else {
            Assertions.assertEquals(hopsEntity, fetchedHopsEntity.get());
        }
        //cleanup
        hopsDbService.deleteAll();
    }

}

package com.cokroktoupadek.beersandmealsbackend.h2_test_db.service.beer;

import com.cokroktoupadek.beersandmealsbackend.domain.entity.beer.AmountEntity;
import com.cokroktoupadek.beersandmealsbackend.domain.entity.beer.HopsEntity;
import com.cokroktoupadek.beersandmealsbackend.service.beer.AmountDbService;
import com.cokroktoupadek.beersandmealsbackend.service.beer.HopsDbService;
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

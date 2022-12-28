package com.cokroktoupadek.beersandmealsbackend.h2_test_db.service.beer;

import com.cokroktoupadek.beersandmealsbackend.domain.entity.beer.AmountEntity;
import com.cokroktoupadek.beersandmealsbackend.service.beer.AmountDbService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Transactional
@SpringBootTest
@TestPropertySource("classpath:application-H2TestDb.properties")
class AmountDbServiceTest {

    @Autowired
    AmountDbService amountDbService;
    @Test
    void findByIdTest()  {
        //given
        AmountEntity amountEntity=new AmountEntity(1.0,"testAmount");
        //when
        amountDbService.save(amountEntity);
        //then
        Long id=amountEntity.getId();
        Optional<AmountEntity> fetchedAmountEntity= amountDbService.findById(id);
        if (fetchedAmountEntity.isEmpty()){
            Assertions.fail("fetched value is empty");
        }else {
            Assertions.assertEquals(fetchedAmountEntity.get().getId(), amountEntity.getId());
        }
        //cleanup
        amountDbService.deleteAll();
    }

    @Test
    void findByValueAndUnitTest()  {
        //given
        AmountEntity amountEntity=new AmountEntity(1.0,"testAmount");
        //when
        amountDbService.save(amountEntity);
        //then
        Optional<AmountEntity> fetchedAmountEntity= amountDbService.findByValueAndUnit(1.0,"testAmount");
        if (fetchedAmountEntity.isEmpty()){
            Assertions.fail("fetched value is empty");
        }else {
            Assertions.assertEquals(fetchedAmountEntity.get(), amountEntity);
        }
        //cleanup
        amountDbService.deleteAll();
    }

    @Test
    void findAllTest()  {
        //given
        AmountEntity amountEntity0=new AmountEntity(1.0,"testAmount");
        AmountEntity amountEntity1=new AmountEntity(1.0,"testAmount");
        //when
        amountDbService.save(amountEntity0);
        amountDbService.save(amountEntity1);

        List<AmountEntity> amountEntityList= amountDbService.findAll();
        if (amountEntityList.isEmpty()){
            Assertions.fail("fetched value is empty");
        }else {
            Assertions.assertEquals(2, amountEntityList.size());
        }
        //cleanup
        amountDbService.deleteAll();
    }

}
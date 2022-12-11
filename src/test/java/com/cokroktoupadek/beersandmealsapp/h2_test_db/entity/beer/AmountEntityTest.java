package com.cokroktoupadek.beersandmealsapp.h2_test_db.entity.beer;

import com.cokroktoupadek.beersandmealsapp.domain.entity.beer.AmountEntity;
import com.cokroktoupadek.beersandmealsapp.repository.beer.AmountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

@SpringBootTest
@TestPropertySource("classpath:application-H2TestDb.properties")
class AmountEntityTest {

    @Autowired
    AmountRepository amountRepository;

    @Test
    void addAmountTest(){
        //given
        AmountEntity amountEntity=new AmountEntity(1.0,"testAmount");
        //when
        amountRepository.save(amountEntity);
        //then
        Long id=amountEntity.getId();
        Optional<AmountEntity> fetchedAmountEntity=amountRepository.findById(id);
        if (fetchedAmountEntity.isEmpty()){
            Assertions.fail("fetched value is empty");
        }else {
            Assertions.assertEquals(fetchedAmountEntity.get().getId(), amountEntity.getId());
        }
        //cleanup
        amountRepository.deleteAll();
    }

}
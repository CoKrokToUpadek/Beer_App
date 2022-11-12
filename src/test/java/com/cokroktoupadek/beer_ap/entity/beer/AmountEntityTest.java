package com.cokroktoupadek.beer_ap.entity.beer;

import com.cokroktoupadek.beer_ap.domain.entity.beer.AmountEntity;
import com.cokroktoupadek.beer_ap.domain.entity.beer.TempEntity;
import com.cokroktoupadek.beer_ap.repository.beer.AmountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
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
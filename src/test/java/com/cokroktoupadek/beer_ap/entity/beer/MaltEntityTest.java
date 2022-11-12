package com.cokroktoupadek.beer_ap.entity.beer;

import com.cokroktoupadek.beer_ap.domain.entity.beer.AmountEntity;
import com.cokroktoupadek.beer_ap.domain.entity.beer.MaltEntity;
import com.cokroktoupadek.beer_ap.repository.beer.AmountRepository;
import com.cokroktoupadek.beer_ap.repository.beer.MaltRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class MaltEntityTest {

    @Autowired
    MaltRepository maltRepository;



    @Test
    void addMaltTest(){
        //given
        AmountEntity amountEntity=new AmountEntity(1.0,"testAmount");
        MaltEntity maltEntity=new MaltEntity("testMalt",amountEntity);

        //when
        maltRepository.save(maltEntity);
        //then
        Long id=maltEntity.getId();
        Optional<MaltEntity> fetchedMaltEntity=maltRepository.findById(id);
        if (fetchedMaltEntity.isEmpty()){
            Assertions.fail("fetched value is empty");
        }else {
            Assertions.assertEquals(fetchedMaltEntity.get().getId(), maltEntity.getId());
        }
        //cleanup
        maltRepository.deleteAll();
    }

}
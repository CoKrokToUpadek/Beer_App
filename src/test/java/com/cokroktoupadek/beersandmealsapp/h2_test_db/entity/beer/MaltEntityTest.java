package com.cokroktoupadek.beersandmealsapp.h2_test_db.entity.beer;

import com.cokroktoupadek.beersandmealsapp.domain.entity.beer.AmountEntity;
import com.cokroktoupadek.beersandmealsapp.domain.entity.beer.MaltEntity;
import com.cokroktoupadek.beersandmealsapp.repository.beer.AmountRepository;
import com.cokroktoupadek.beersandmealsapp.repository.beer.MaltRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

@SpringBootTest
@TestPropertySource("classpath:application-H2TestDb.properties")
class MaltEntityTest {

    @Autowired
    MaltRepository maltRepository;

    @Autowired
    AmountRepository amountRepository;



    @Test
    void addMaltTest(){
        //given
        AmountEntity amountEntity=new AmountEntity(1.0,"testAmount");
        amountRepository.save(amountEntity);
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
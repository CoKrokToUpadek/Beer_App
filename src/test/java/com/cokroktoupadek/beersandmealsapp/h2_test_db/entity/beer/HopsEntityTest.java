package com.cokroktoupadek.beersandmealsapp.h2_test_db.entity.beer;

import com.cokroktoupadek.beersandmealsapp.domain.entity.beer.AmountEntity;
import com.cokroktoupadek.beersandmealsapp.domain.entity.beer.HopsEntity;
import com.cokroktoupadek.beersandmealsapp.repository.beer.AmountRepository;
import com.cokroktoupadek.beersandmealsapp.repository.beer.HopsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

@SpringBootTest
@TestPropertySource("classpath:application-H2TestDb.properties")
public class HopsEntityTest {
    @Autowired
    HopsRepository hopsRepository;

    @Autowired
    AmountRepository amountRepository;



    @Test
    void addHopsTest(){
        //given
        AmountEntity amountEntity=new AmountEntity(2.0,"testAmount");
        amountRepository.save(amountEntity);
        HopsEntity hopsEntity =new HopsEntity("testName",amountEntity,"testAdd","testAttribute");
        //when
        hopsRepository.save(hopsEntity);
        //then
        Long id= hopsEntity.getId();
        Optional<HopsEntity> fetchedHopsEntity = hopsRepository.findById(id);
        if (fetchedHopsEntity.isEmpty()){
            Assertions.fail("fetched value is empty");
        }else {
            Assertions.assertEquals(fetchedHopsEntity.get().getId(), hopsEntity.getId());
        }
        //cleanup
        hopsRepository.deleteAll();
    }

}

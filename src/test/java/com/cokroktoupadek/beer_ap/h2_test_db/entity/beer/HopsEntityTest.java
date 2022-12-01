package com.cokroktoupadek.beer_ap.h2_test_db.entity.beer;

import com.cokroktoupadek.beer_ap.domain.entity.beer.AmountEntity;
import com.cokroktoupadek.beer_ap.domain.entity.beer.HopsEntity;
import com.cokroktoupadek.beer_ap.repository.beer.AmountRepository;
import com.cokroktoupadek.beer_ap.repository.beer.HopsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

@SpringBootTest
@TestPropertySource("classpath:application-H2TestDb.properties")
public class HopsEntityTest {
    @Autowired
    HopsRepository hopsRepository;



    @Test
    void addHopsTest(){
        //given
        AmountEntity amountEntity=new AmountEntity(2.0,"testAmount");
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

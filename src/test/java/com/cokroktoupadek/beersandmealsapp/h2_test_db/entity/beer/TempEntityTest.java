package com.cokroktoupadek.beersandmealsapp.h2_test_db.entity.beer;

import com.cokroktoupadek.beersandmealsapp.domain.entity.beer.TempEntity;
import com.cokroktoupadek.beersandmealsapp.repository.beer.TempRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;


@SpringBootTest
@TestPropertySource("classpath:application-H2TestDb.properties")
class TempEntityTest {
    @Autowired
    TempRepository tempRepository;

    @Test
    void addTempTest(){
        //given
        TempEntity tempEntity=new TempEntity(1,"testTemp");
        //when
        tempRepository.save(tempEntity);
        //then
        Long id=tempEntity.getId();
        Optional<TempEntity>fetchedTempEntity=tempRepository.findById(id);
        if (fetchedTempEntity.isEmpty()){
            Assertions.fail("fetched value is empty");
        }else {
            Assertions.assertEquals(fetchedTempEntity.get().getId(), tempEntity.getId());
        }
        //cleanup
        tempRepository.deleteAll();
    }

}
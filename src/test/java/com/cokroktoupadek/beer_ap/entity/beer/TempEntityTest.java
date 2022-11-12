package com.cokroktoupadek.beer_ap.entity.beer;

import com.cokroktoupadek.beer_ap.domain.entity.beer.TempEntity;
import com.cokroktoupadek.beer_ap.repository.beer.TempRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;


@SpringBootTest
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
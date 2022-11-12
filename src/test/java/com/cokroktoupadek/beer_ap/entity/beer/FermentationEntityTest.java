package com.cokroktoupadek.beer_ap.entity.beer;

import com.cokroktoupadek.beer_ap.domain.entity.beer.FermentationEntity;
import com.cokroktoupadek.beer_ap.domain.entity.beer.TempEntity;
import com.cokroktoupadek.beer_ap.repository.beer.FermentationRepository;
import com.cokroktoupadek.beer_ap.repository.beer.TempRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class FermentationEntityTest {

    @Autowired
    FermentationRepository fermentationRepository;



    @Test
    void addFermentationTest(){
        //given
        TempEntity tempEntity=new TempEntity(1,"testTemp");
        FermentationEntity fermentationEntity =new FermentationEntity(tempEntity);
        //when
        fermentationRepository.save(fermentationEntity);
        //then
        Long id=fermentationEntity.getId();
        Optional<FermentationEntity> fetchedFermentationEntity =fermentationRepository.findById(id);
        if (fetchedFermentationEntity.isEmpty()){
            Assertions.fail("fetched value is empty");
        }else {
            Assertions.assertEquals(fetchedFermentationEntity.get().getId(), fermentationEntity.getId());
        }
        //cleanup
        fermentationRepository.deleteAll();
    }

}


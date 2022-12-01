package com.cokroktoupadek.beer_ap.h2_test_db.entity.beer;

import com.cokroktoupadek.beer_ap.domain.entity.beer.*;
import com.cokroktoupadek.beer_ap.repository.beer.FermentationRepository;
import com.cokroktoupadek.beer_ap.repository.beer.MashTempRepository;
import com.cokroktoupadek.beer_ap.repository.beer.MethodRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@TestPropertySource("classpath:application-H2TestDb.properties")
class MethodEntityTest {
    @Autowired
    MethodRepository methodRepository;


    @Test
    void addMethodTest(){
        //given
        TempEntity tempEntity=new TempEntity(1,"testTemp");
        MashTempEntity mashTempEntity =new MashTempEntity(tempEntity,1);
        List<MashTempEntity> mashTempEntityList=new ArrayList<>(List.of(mashTempEntity));
        FermentationEntity fermentationEntity=new FermentationEntity(new TempEntity(1,"testTemp"));
        MethodEntity methodEntity =new MethodEntity(mashTempEntityList,fermentationEntity);
        //when
        methodRepository.save(methodEntity);
        //then
        Long id= methodEntity.getId();
        Optional<MethodEntity> fetchedMethodEntity = methodRepository.findById(id);

        if (fetchedMethodEntity.isEmpty()){
            Assertions.fail("fetched value is empty");
        }else {
            Assertions.assertEquals(fetchedMethodEntity.get().getId(), methodEntity.getId());
        }
        //cleanup
        methodRepository.deleteAll();


    }
}
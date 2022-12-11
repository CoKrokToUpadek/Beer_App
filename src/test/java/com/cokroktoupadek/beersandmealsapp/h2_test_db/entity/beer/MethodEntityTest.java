package com.cokroktoupadek.beersandmealsapp.h2_test_db.entity.beer;

import com.cokroktoupadek.beersandmealsapp.domain.entity.beer.*;
import com.cokroktoupadek.beersandmealsapp.repository.beer.MethodRepository;
import com.cokroktoupadek.beersandmealsapp.repository.beer.TempRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@TestPropertySource("classpath:application-H2TestDb.properties")
class MethodEntityTest {
    @Autowired
    MethodRepository methodRepository;

    @Autowired
    TempRepository tempRepository;


    @Test
    void addMethodTest(){
        //given
        TempEntity tempEntity=new TempEntity(1,"testTemp");
        tempRepository.save(tempEntity);
        MashTempEntity mashTempEntity =new MashTempEntity(tempEntity,1);
        List<MashTempEntity> mashTempEntityList=new ArrayList<>(List.of(mashTempEntity));
        FermentationEntity fermentationEntity=new FermentationEntity(tempEntity);
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
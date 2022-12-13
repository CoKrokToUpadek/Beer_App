package com.cokroktoupadek.beersandmealsapp.h2_test_db.dbservice.beer;

import com.cokroktoupadek.beersandmealsapp.domain.entity.beer.AmountEntity;
import com.cokroktoupadek.beersandmealsapp.domain.entity.beer.HopsEntity;
import com.cokroktoupadek.beersandmealsapp.domain.entity.beer.IngredientsEntity;
import com.cokroktoupadek.beersandmealsapp.domain.entity.beer.MaltEntity;
import com.cokroktoupadek.beersandmealsapp.service.beer.AmountDbService;
import com.cokroktoupadek.beersandmealsapp.service.beer.IngredientsDbService;
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
class IngredientsDbServiceTest {
    @Autowired
    IngredientsDbService ingredientsDbService;

    @Autowired
    AmountDbService amountDbService;

    @Test
    void addIngredientsTest(){
        //given

        AmountEntity amountEntity=new AmountEntity(1.0,"testAmount");
        amountDbService.save(amountEntity);
        MaltEntity maltEntity=new MaltEntity("testMalt",amountEntity);
        HopsEntity hopsEntity =new HopsEntity("testName",amountEntity,"testAdd","testAttribute");
        List<MaltEntity> maltEntityList=new ArrayList<>(List.of(maltEntity));
        List<HopsEntity> hopsEntityList=new ArrayList<>(List.of(hopsEntity));
        IngredientsEntity ingredientsEntity=new IngredientsEntity(maltEntityList,hopsEntityList,"testYeast");
        //when
        ingredientsDbService.save(ingredientsEntity);
        //then
        Long id=ingredientsEntity.getId();
        Optional<IngredientsEntity> fetchedIngredientsEntity = ingredientsDbService.findById(id);

        if (fetchedIngredientsEntity.isEmpty()){
            Assertions.fail("fetched value is empty");
        }else {
            Assertions.assertEquals(ingredientsEntity, fetchedIngredientsEntity.get());
        }
        //cleanup
        ingredientsDbService.deleteAll();


    }

}
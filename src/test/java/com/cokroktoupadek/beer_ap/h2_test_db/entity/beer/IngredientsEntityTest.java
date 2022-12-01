package com.cokroktoupadek.beer_ap.h2_test_db.entity.beer;

import com.cokroktoupadek.beer_ap.domain.entity.beer.AmountEntity;
import com.cokroktoupadek.beer_ap.domain.entity.beer.HopsEntity;
import com.cokroktoupadek.beer_ap.domain.entity.beer.IngredientsEntity;
import com.cokroktoupadek.beer_ap.domain.entity.beer.MaltEntity;
import com.cokroktoupadek.beer_ap.repository.beer.HopsRepository;
import com.cokroktoupadek.beer_ap.repository.beer.IngredientsRepository;
import com.cokroktoupadek.beer_ap.repository.beer.MaltRepository;
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
class IngredientsEntityTest {
    @Autowired
    IngredientsRepository ingredientsRepository;

    @Test
    void addIngredientsTest(){
        //given

        AmountEntity amountEntity=new AmountEntity(1.0,"testAmount");
        MaltEntity maltEntity=new MaltEntity("testMalt",amountEntity);
        HopsEntity hopsEntity =new HopsEntity("testName",amountEntity,"testAdd","testAttribute");
        List<MaltEntity> maltEntityList=new ArrayList<>(List.of(maltEntity));
        List<HopsEntity> hopsEntityList=new ArrayList<>(List.of(hopsEntity));
        IngredientsEntity ingredientsEntity=new IngredientsEntity(maltEntityList,hopsEntityList,"testYeast");
        //when
        ingredientsRepository.save(ingredientsEntity);
        //then
        Long id=ingredientsEntity.getId();
        Optional<IngredientsEntity> fetchedIngredientsEntity = ingredientsRepository.findById(id);

        if (fetchedIngredientsEntity.isEmpty()){
            Assertions.fail("fetched value is empty");
        }else {
            Assertions.assertEquals(fetchedIngredientsEntity.get().getId(), ingredientsEntity.getId());
            Assertions.assertEquals(fetchedIngredientsEntity.get().getMaltsList().get(0).getId(), ingredientsEntity.getMaltsList().get(0).getId());


        }
        //cleanup
        ingredientsRepository.deleteAll();


    }

}
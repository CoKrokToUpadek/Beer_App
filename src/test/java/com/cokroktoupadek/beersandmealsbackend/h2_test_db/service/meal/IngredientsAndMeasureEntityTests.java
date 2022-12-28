package com.cokroktoupadek.beersandmealsbackend.h2_test_db.service.meal;

import com.cokroktoupadek.beersandmealsbackend.domain.entity.meal.IngredientAndMeasureEntity;
import com.cokroktoupadek.beersandmealsbackend.repository.meal.IngredientAndMeasureRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import javax.transaction.Transactional;
import java.util.Optional;
@Transactional
@SpringBootTest
@TestPropertySource("classpath:application-H2TestDb.properties")
public class IngredientsAndMeasureEntityTests {

    @Autowired
    IngredientAndMeasureRepository ingredientAndMeasureRepository;

    @Test
    void addIngredientTest(){
        //given
        IngredientAndMeasureEntity ingredientsEntity=new IngredientAndMeasureEntity("test ingredient","test measure");
        //when
        ingredientAndMeasureRepository.save(ingredientsEntity);
        //then
        Long id=ingredientsEntity.getId();
        Optional<IngredientAndMeasureEntity> fetchedIngredientEntity =ingredientAndMeasureRepository.findById(id);
        if (fetchedIngredientEntity.isEmpty()){
            Assertions.fail("fetched value is empty");
        }else {
            Assertions.assertEquals(fetchedIngredientEntity.get().getId(), ingredientsEntity.getId());
        }
        //cleanup
        ingredientAndMeasureRepository.deleteAll();
    }
}

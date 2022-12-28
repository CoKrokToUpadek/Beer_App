package com.cokroktoupadek.beersandmealsbackend.h2_test_db.service.meal;

import com.cokroktoupadek.beersandmealsbackend.domain.entity.meal.IngredientAndMeasureEntity;
import com.cokroktoupadek.beersandmealsbackend.domain.entity.meal.MealEntity;
import com.cokroktoupadek.beersandmealsbackend.repository.meal.MealRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Optional;
@Transactional
@SpringBootTest
@TestPropertySource("classpath:application-H2TestDb.properties")
public class MealEntityTest {
    @Autowired
    MealRepository mealRepository;

    @Test
    void addMealTest(){
        //given
        MealEntity mealEntity =new MealEntity(1L,"tname","tcategory","tarea","tinstructions","tthumbnail",
                "ttags","tyoutubelink", new ArrayList<>(),"tsource",new ArrayList<>());
        mealEntity.getIngredientsAndMeasureEntityList().add(new IngredientAndMeasureEntity("tigredient","tmeasure"));
        //when
        mealRepository.save(mealEntity);
        //then
        Long id= mealEntity.getId();
        Optional<MealEntity> fetchedMealEntity = mealRepository.findById(id);
        if (fetchedMealEntity.isEmpty()){
            Assertions.fail("fetched value is empty");
        }else {
            Assertions.assertEquals(fetchedMealEntity.get().getId(), mealEntity.getId());
        }
        //cleanup
        mealRepository.deleteAll();
    }
}

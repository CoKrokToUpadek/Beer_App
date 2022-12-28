package com.cokroktoupadek.beersandmealsbackend.repository.meal;


import com.cokroktoupadek.beersandmealsbackend.domain.entity.meal.IngredientAndMeasureEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Transactional
@Repository
public interface IngredientAndMeasureRepository extends CrudRepository<IngredientAndMeasureEntity, Long> {

    @Override
    List<IngredientAndMeasureEntity> findAll();
    @Override
    Optional<IngredientAndMeasureEntity> findById(Long id);

    Optional<IngredientAndMeasureEntity> findByIngredientName(String ingredients);


    @Override
    IngredientAndMeasureEntity save(IngredientAndMeasureEntity ingredientAndMeasureEntity);

    @Override
    void deleteById(Long id);

    @Override
    void deleteAll();
}

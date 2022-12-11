package com.cokroktoupadek.beersandmealsapp.repository.meal;


import com.cokroktoupadek.beersandmealsapp.domain.entity.meal.MealEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface MealRepository extends CrudRepository<MealEntity, Long> {

    @Override
    List<MealEntity> findAll();
    @Override
    Optional<MealEntity> findById(Long id);

    Optional<MealEntity> findByName(String name);

    @Override
    MealEntity save(MealEntity mealEntity);

    @Override
    void deleteById(Long id);

    @Override
    void deleteAll();
}

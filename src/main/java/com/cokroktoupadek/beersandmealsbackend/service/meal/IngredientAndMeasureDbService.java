package com.cokroktoupadek.beersandmealsbackend.service.meal;


import com.cokroktoupadek.beersandmealsbackend.domain.entity.meal.IngredientAndMeasureEntity;
import com.cokroktoupadek.beersandmealsbackend.repository.meal.IngredientAndMeasureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class IngredientAndMeasureDbService {
    IngredientAndMeasureRepository ingredientAndMeasureRepository;
    @Autowired
    public IngredientAndMeasureDbService(IngredientAndMeasureRepository ingredientAndMeasureRepository) {
        this.ingredientAndMeasureRepository = ingredientAndMeasureRepository;
    }

    private IngredientAndMeasureEntity save(IngredientAndMeasureEntity ingredientAndMeasureEntity){
        return ingredientAndMeasureRepository.save(ingredientAndMeasureEntity);
    }

    public IngredientAndMeasureEntity findById(Long id)  {
        return ingredientAndMeasureRepository.findById(id).orElse(null);
    }

    public IngredientAndMeasureEntity findByIngredient(String ingredient){
        return ingredientAndMeasureRepository.findByIngredientName(ingredient).orElse(null);
    }

    List<IngredientAndMeasureEntity> findAll() {
        return ingredientAndMeasureRepository.findAll();
    }

    void DeleteById(Long id) {
        ingredientAndMeasureRepository.deleteById(id);
    }

    void DeleteAll() {
        ingredientAndMeasureRepository.deleteAll();
    }
}

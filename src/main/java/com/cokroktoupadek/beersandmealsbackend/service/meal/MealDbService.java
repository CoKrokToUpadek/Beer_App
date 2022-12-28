package com.cokroktoupadek.beersandmealsbackend.service.meal;


import com.cokroktoupadek.beersandmealsbackend.domain.entity.meal.MealEntity;
import com.cokroktoupadek.beersandmealsbackend.repository.meal.MealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MealDbService {

    MealRepository mealRepository;
    @Autowired
    public MealDbService(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    public MealEntity save(MealEntity mealEntity){
        return mealRepository.save(mealEntity);
    }

    public void mealDuplicateVerifyAndSave(MealEntity mealEntity){
        if (mealRepository.findByName(mealEntity.getName()).isEmpty()){
            save(mealEntity);
        }
    }

    public MealEntity findById(Long id)  {
        return mealRepository.findById(id).orElse(null);
    }

    public Optional<MealEntity> findByName(String name){
        return mealRepository.findByName(name);
    }

   public List<MealEntity> findAll() {
        return mealRepository.findAll();
    }

    public void deleteById(Long id) {
        mealRepository.deleteById(id);
    }

   public void deleteAll() {
        mealRepository.deleteAll();
    }


}

package com.cokroktoupadek.beersandmealsbackend.service.beer;

import com.cokroktoupadek.beersandmealsbackend.domain.entity.beer.IngredientsEntity;
import com.cokroktoupadek.beersandmealsbackend.repository.beer.IngredientsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class IngredientsDbService {

   private IngredientsRepository ingredientsRepository;
    @Autowired
    public IngredientsDbService(IngredientsRepository ingredientsRepository) {
        this.ingredientsRepository = ingredientsRepository;
    }

    public IngredientsEntity save(IngredientsEntity ingredientsEntity){
        return ingredientsRepository.save(ingredientsEntity);
    }

    public Optional<IngredientsEntity> findById(Long id) {
        return ingredientsRepository.findById(id);
    }

    public List<IngredientsEntity> findById() {
        return ingredientsRepository.findAll();
    }

    public void deleteById(Long id){
        ingredientsRepository.deleteById(id);
    }

    public void deleteAll(){
        ingredientsRepository.deleteAll();
    }
}

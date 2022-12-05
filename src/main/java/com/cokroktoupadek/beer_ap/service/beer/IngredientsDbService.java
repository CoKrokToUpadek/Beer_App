package com.cokroktoupadek.beer_ap.service.beer;

import com.cokroktoupadek.beer_ap.domain.entity.beer.IngredientsEntity;
import com.cokroktoupadek.beer_ap.repository.beer.IngredientsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class IngredientsDbService {

   private IngredientsRepository ingredientsRepository;
    @Autowired
    public IngredientsDbService(IngredientsRepository ingredientsRepository) {
        this.ingredientsRepository = ingredientsRepository;
    }

    IngredientsEntity save(IngredientsEntity ingredientsEntity){
        return ingredientsRepository.save(ingredientsEntity);
    }

    IngredientsEntity findById(Long id) throws Exception {
        return ingredientsRepository.findById(id).orElseThrow(Exception::new);
    }

    List<IngredientsEntity> findById() {
        return ingredientsRepository.findAll();
    }

    void deleteById(Long id){
        ingredientsRepository.deleteById(id);
    }

    void deleteAll(){
        ingredientsRepository.deleteAll();
    }
}

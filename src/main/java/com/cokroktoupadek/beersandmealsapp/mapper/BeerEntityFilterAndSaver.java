package com.cokroktoupadek.beersandmealsapp.mapper;

import com.cokroktoupadek.beersandmealsapp.domain.entity.beer.BeerEntity;


import com.cokroktoupadek.beersandmealsapp.service.beer.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class BeerEntityFilterAndSaver {


    private BeerAndMealEntityManipulatorDbService beerAndMealEntityManipulatorDbService;
    @Autowired
    public BeerEntityFilterAndSaver(BeerAndMealEntityManipulatorDbService beerAndMealEntityManipulatorDbService) {
        this.beerAndMealEntityManipulatorDbService = beerAndMealEntityManipulatorDbService;
    }

    public BeerEntity beerEntityDataOptimizer(BeerEntity beerEntity){
            beerEntity.setBoilVolume(beerAndMealEntityManipulatorDbService.boilVolumeDuplicateVerifier(beerEntity.getBoilVolume()));
            beerEntity.setVolume(beerAndMealEntityManipulatorDbService.volumeDuplicateVerifier(beerEntity.getVolume()));
            beerEntity.getIngredients().getMaltsList().forEach(e->e.setAmount(beerAndMealEntityManipulatorDbService.amountDuplicateVerifier(e.getAmount())));
            beerEntity.getIngredients().getHopsList().forEach(e->e.setAmount(beerAndMealEntityManipulatorDbService.amountDuplicateVerifier(e.getAmount())));
            beerEntity.getMethod().getFermentation().setTemp(beerAndMealEntityManipulatorDbService.tempDuplicateVerifier(beerEntity.getMethod().getFermentation().getTemp()));
            beerEntity.getMethod().getMashTempsList().forEach(e->e.setTemp(beerAndMealEntityManipulatorDbService.tempDuplicateVerifier(e.getTemp())));
       return beerEntity;
   }

   public void beerEntitySaver(BeerEntity beerEntity){
       if (!beerAndMealEntityManipulatorDbService.beerExistenceInDbVerifier(beerEntity)){
           beerAndMealEntityManipulatorDbService.beerEntitySaver(beerEntityDataOptimizer(beerEntity));

       }
   }

}



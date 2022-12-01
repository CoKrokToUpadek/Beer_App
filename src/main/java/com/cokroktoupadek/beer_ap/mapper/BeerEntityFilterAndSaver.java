package com.cokroktoupadek.beer_ap.mapper;

import com.cokroktoupadek.beer_ap.domain.entity.beer.BeerEntity;
import com.cokroktoupadek.beer_ap.domain.entity.beer.MashTempEntity;
import com.cokroktoupadek.beer_ap.service.beer.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BeerEntityFilterAndSaver {

   @Autowired
   DuplicateFilterDbService duplicateFilterDbService;


   public void beerEntityCleanupAndSave(BeerEntity beerEntity){
            beerEntity.setBoilVolume(duplicateFilterDbService.boilVolumeDuplicateVerifier(beerEntity.getBoilVolume()));
            beerEntity.setVolume(duplicateFilterDbService.volumeDuplicateVerifier(beerEntity.getVolume()));
            beerEntity.getIngredients().getMaltsList().forEach(e->e.setAmount(duplicateFilterDbService.amountDuplicateVerifier(e.getAmount())));
            beerEntity.getIngredients().getHopsList().forEach(e->e.setAmount(duplicateFilterDbService.amountDuplicateVerifier(e.getAmount())));
            beerEntity.getMethod().getFermentation().setTemp(duplicateFilterDbService.tempDuplicateVerifier(beerEntity.getMethod().getFermentation().getTemp()));
            beerEntity.getMethod().setFermentation(duplicateFilterDbService.fermentationDuplicateVerifier(beerEntity.getMethod().getFermentation()));
            beerEntity.getMethod().getMashTempsList().forEach(e->e.setTemp(duplicateFilterDbService.tempDuplicateVerifier(e.getTemp())));
            beerEntity.getMethod().setMashTempsList(mashTempEntityListHelper(beerEntity.getMethod().getMashTempsList()));
   }

   public List<MashTempEntity> mashTempEntityListHelper(List<MashTempEntity> mashTempEntityList){
       List<MashTempEntity> outputList=new ArrayList<>();
       for (MashTempEntity mashTempEntity:mashTempEntityList){
           outputList.add(duplicateFilterDbService.mashTempDuplicateVerifier(mashTempEntity));
       }
       return outputList;
   }

}



package com.cokroktoupadek.beer_ap.service.beer;

import com.cokroktoupadek.beer_ap.domain.entity.beer.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BeerEntityManipulatorDbService {
    @Autowired
    BoilVolumeDbService boilVolumeDbService;
    @Autowired
    VolumeDbService volumeDbService;
    @Autowired
    TempDbService tempDbService;
    @Autowired
    AmountDbService amountDbService;
    @Autowired
    FermentationDbService fermentationDbService;
    @Autowired
    MashTempDbService mashTempDbService;
    @Autowired
    MaltDbService maltDbService;
    @Autowired
    HopsDbService hopsDbService;
    @Autowired
    MethodDbService methodDbService;
    @Autowired
    IngredientsDbService ingredientsDbService;

    @Autowired
    BeerDbService beerDbService;


    public AmountEntity amountDuplicateVerifier(AmountEntity amountEntity){
        if(amountDbService.findByValueAndUnit(amountEntity.getValue(),amountEntity.getUnit()).isPresent()){
            return amountDbService.findByValueAndUnit(amountEntity.getValue(), amountEntity.getUnit()).get();        }
        else {
            amountDbService.save(amountEntity);
            return amountEntity;
        }
    }

    public BoilVolumeEntity boilVolumeDuplicateVerifier(BoilVolumeEntity boilVolumeEntity){
        if (boilVolumeDbService. findByUnitAndValue(boilVolumeEntity.getUnit(),boilVolumeEntity.getValue()).isPresent()){
            return boilVolumeDbService.findByUnitAndValue(boilVolumeEntity.getUnit(),boilVolumeEntity.getValue()).get();
        }else {
            boilVolumeDbService.save(boilVolumeEntity);
            return boilVolumeEntity;
        }
    }

    public TempEntity tempDuplicateVerifier(TempEntity tempEntity){
        if (tempDbService.findByValueAndUnit(tempEntity.getValue(), tempEntity.getUnit()).isPresent()){
            return tempDbService.findByValueAndUnit(tempEntity.getValue(), tempEntity.getUnit()).get();
        }else {
            tempDbService.save(tempEntity);
            return tempEntity;
        }
    }

    public VolumeEntity volumeDuplicateVerifier(VolumeEntity volumeEntity){
        if (volumeDbService.findByUnitAndValue(volumeEntity.getUnit(), volumeEntity.getValue()).isPresent()){
            return volumeDbService.findByUnitAndValue(volumeEntity.getUnit(), volumeEntity.getValue()).get();
        }else {
            volumeDbService.save(volumeEntity);
            return volumeEntity;
        }
    }

    public boolean beerExistenceInDbVerifier(BeerEntity beerEntity){
        return beerDbService.findByName(beerEntity.getName()).isPresent();
    }




    public void beerEntitySaver(BeerEntity beerEntity){
        fermentationDbService.save(beerEntity.getMethod().getFermentation());
        beerEntity.getMethod().getMashTempsList().forEach(mashTempDbService::save);
        methodDbService.save(beerEntity.getMethod());
        beerEntity.getIngredients().getHopsList().forEach(hopsDbService::save);
        beerEntity.getIngredients().getMaltsList().forEach(maltDbService::save);
        ingredientsDbService.save(beerEntity.getIngredients());

        beerDbService.save(beerEntity);

    }


}

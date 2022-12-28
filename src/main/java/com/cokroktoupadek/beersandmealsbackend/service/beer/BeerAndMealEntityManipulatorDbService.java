package com.cokroktoupadek.beersandmealsbackend.service.beer;

import com.cokroktoupadek.beersandmealsbackend.domain.entity.beer.*;
import com.cokroktoupadek.beersandmealsbackend.domain.entity.meal.MealEntity;
import com.cokroktoupadek.beersandmealsbackend.service.meal.MealDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BeerAndMealEntityManipulatorDbService {

   private BoilVolumeDbService boilVolumeDbService;

    private VolumeDbService volumeDbService;

    private TempDbService tempDbService;

    private  AmountDbService amountDbService;

    private  BeerDbService beerDbService;

    private MealDbService mealDbService;

    @Autowired
    public BeerAndMealEntityManipulatorDbService(BoilVolumeDbService boilVolumeDbService, VolumeDbService volumeDbService,
                                                 TempDbService tempDbService, AmountDbService amountDbService, BeerDbService beerDbService, MealDbService mealDbService) {
        this.boilVolumeDbService = boilVolumeDbService;
        this.volumeDbService = volumeDbService;
        this.tempDbService = tempDbService;
        this.amountDbService = amountDbService;
        this.beerDbService = beerDbService;
        this.mealDbService = mealDbService;
    }

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
        //moved here for my own convenience
        beerDbService.save(beerEntity);

    }

    public void beerEntityDeleter(String name){
        BeerEntity beerEntity= beerDbService.findByName(name).get();
        TempEntity tempEntity;
        List<MashTempEntity> mashTempEntityList;
        List<HopsEntity> hopsEntityList;
        List<MaltEntity> maltEntityList;

        beerEntity.getVolume().getBeerVolumes().remove(beerEntity);
        beerEntity.getBoilVolume().getBeerBoilVolumes().remove(beerEntity);

        tempEntity=beerEntity.getMethod().getFermentation().getTemp();
        tempEntity.getFermentationTempList().remove(beerEntity.getMethod().getFermentation());
        tempDbService.save(tempEntity);
        mashTempEntityList=beerEntity.getMethod().getMashTempsList();
        mashTempEntityList.forEach(e->{
            TempEntity lambdaTempEntity=e.getTemp();
            lambdaTempEntity.getMashTempList().remove(e);
            tempDbService.save(lambdaTempEntity);
        });
       maltEntityList= beerEntity.getIngredients().getMaltsList();
       maltEntityList.forEach(e->{
           AmountEntity lambdaAmountEntity=e.getAmount();
           lambdaAmountEntity.getMaltsList().remove(e);
           amountDbService.save(lambdaAmountEntity);
       });
        hopsEntityList= beerEntity.getIngredients().getHopsList();
        hopsEntityList.forEach(e->{
            AmountEntity lambdaAmountEntity=e.getAmount();
            lambdaAmountEntity.getHopsList().remove(e);
            amountDbService.save(lambdaAmountEntity);
        });
        beerEntity.getBeerFavouredBy().forEach(e->e.getFavouredBeers().remove(beerEntity));
        beerDbService.deleteById(beerEntity.getId());
    }

    public void mealEntityDeleter(String name){
        MealEntity mealEntity= mealDbService.findByName(name).get();
        mealEntity.getMealFavouredBy().forEach(e->e.getFavouredMeals().remove(mealEntity));
        mealDbService.deleteById(mealEntity.getId());

    }
    public void allMealsEntitiesDeleter(List<String> mealList){
        mealList.forEach(this::mealEntityDeleter);
    }

    public void allBeerEntitiesDeleter(List<String> beerList){
        beerList.forEach(this::beerEntityDeleter);
    }


    public void entitiesWithEmptyRelationsCleaner(){
        List<TempEntity> tempEntityList=tempDbService.findAll();
        tempEntityList.forEach(e->{
         if(e.getMashTempList().isEmpty() && e.getFermentationTempList().isEmpty()){
             tempDbService.deleteById(e.getId());
         }
        });
        List<AmountEntity> amountEntityList=amountDbService.findAll();
        amountEntityList.forEach(e->{
            if (e.getHopsList().isEmpty() && e.getMaltsList().isEmpty()){
                amountDbService.deleteById(e.getId());
            }
        });

        List<VolumeEntity> volumeEntityList=volumeDbService.findAll();
        volumeEntityList.forEach(e->{
            if(e.getBeerVolumes().isEmpty()){
                volumeDbService.deleteById(e.getId());
            }
        });

        List<BoilVolumeEntity> boilVolumeEntityList=boilVolumeDbService.findAll();
        boilVolumeEntityList.forEach(e->{
            if(e.getBeerBoilVolumes().isEmpty()){
                boilVolumeDbService.deleteById(e.getId());
            }
        });

    }


}

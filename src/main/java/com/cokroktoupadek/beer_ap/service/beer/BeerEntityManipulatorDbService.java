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
        beerDbService.save(beerEntity);

    }

    public void beerEntityDeleter(String name){
        BeerEntity beerEntity= beerDbService.findByName(name).get();
        TempEntity tempEntity;
        List<MashTempEntity> mashTempEntityList;
        List<HopsEntity> hopsEntityList;
        List<MaltEntity> maltEntityList;

        //removing relations to entities (I know it's ugly)
        beerEntity.getVolume().getBeerVolumes().remove(beerEntity);
        beerEntity.getBoilVolume().getBeerBoilVolumes().remove(beerEntity);
        beerEntity.setBoilVolume(new BoilVolumeEntity());
        beerEntity.setVolume(new VolumeEntity());
        //handle fermentation relations
        tempEntity=beerEntity.getMethod().getFermentation().getTemp();
        tempEntity.getFermentationTempList().remove(beerEntity.getMethod().getFermentation());
        tempDbService.save(tempEntity);
        beerEntity.getMethod().getFermentation().setTemp(new TempEntity());
        //handle mashtemp and temp
        mashTempEntityList=beerEntity.getMethod().getMashTempsList();
        mashTempEntityList.forEach(e->{
            TempEntity lambdaTempEntity=e.getTemp();
            lambdaTempEntity.getMashTempList().remove(e);
            tempDbService.save(lambdaTempEntity);
            e.setTemp(new TempEntity());
        });
        //handle malts
       maltEntityList= beerEntity.getIngredients().getMaltsList();
       maltEntityList.forEach(e->{
           AmountEntity lambdaAmountEntity=e.getAmount();
           lambdaAmountEntity.getMaltsList().remove(e);
           amountDbService.save(lambdaAmountEntity);
          e.setAmount(new AmountEntity());
       });
        //handle hops
        hopsEntityList= beerEntity.getIngredients().getHopsList();
        hopsEntityList.forEach(e->{
            AmountEntity lambdaAmountEntity=e.getAmount();
            lambdaAmountEntity.getHopsList().remove(e);
            amountDbService.save(lambdaAmountEntity);
            e.setAmount(new AmountEntity());
        });

        beerDbService.deleteById(beerEntity.getId());
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

    }


}

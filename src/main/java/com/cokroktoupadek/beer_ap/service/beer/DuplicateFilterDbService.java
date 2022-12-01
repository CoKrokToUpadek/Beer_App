package com.cokroktoupadek.beer_ap.service.beer;

import com.cokroktoupadek.beer_ap.domain.entity.beer.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DuplicateFilterDbService {
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

    public FermentationEntity fermentationDuplicateVerifier(FermentationEntity fermentationEntity){
        if (!fermentationDbService.findFermentationByTempValueAndUnit(fermentationEntity.getTemp().getUnit(),fermentationEntity.getTemp().getValue()).isEmpty()){
            return fermentationDbService.findFermentationByTempValueAndUnit(fermentationEntity.getTemp().getUnit(),fermentationEntity.getTemp().getValue()).get(0);
        }else {
            fermentationDbService.save(fermentationEntity);
            return fermentationEntity;
        }

    }

}

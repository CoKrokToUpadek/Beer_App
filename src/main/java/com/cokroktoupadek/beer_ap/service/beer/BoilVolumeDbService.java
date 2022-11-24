package com.cokroktoupadek.beer_ap.service.beer;

import com.cokroktoupadek.beer_ap.domain.entity.beer.BeerEntity;
import com.cokroktoupadek.beer_ap.domain.entity.beer.BoilVolumeEntity;
import com.cokroktoupadek.beer_ap.repository.beer.BoilVolumeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BoilVolumeDbService {
    @Autowired
    BoilVolumeRepository boilVolumeRepository;

    BoilVolumeEntity save(BoilVolumeEntity boilVolumeEntity){
        return boilVolumeRepository.save(boilVolumeEntity);
    }

    BoilVolumeEntity findById(Long id) throws Exception {
        return boilVolumeRepository.findById(id).orElseThrow(Exception::new);
    }

   Optional <BoilVolumeEntity> findByUnitAndValue(String unit, Integer value){
        return boilVolumeRepository.findByUnitAndValue(unit,value);
    }

   public BoilVolumeEntity boilVolumeDuplicateVerifier(BoilVolumeEntity boilVolumeEntity){
        if (findByUnitAndValue(boilVolumeEntity.getUnit(),boilVolumeEntity.getValue()).isPresent()){
            return findByUnitAndValue(boilVolumeEntity.getUnit(),boilVolumeEntity.getValue()).get();
        }else {
            return boilVolumeEntity;
        }
    }

    List<BoilVolumeEntity> findById() {
        return boilVolumeRepository.findAll();
    }

    void DeleteById(Long id){
        boilVolumeRepository.deleteById(id);
    }

    void DeleteAll(){
        boilVolumeRepository.deleteAll();
    }

}

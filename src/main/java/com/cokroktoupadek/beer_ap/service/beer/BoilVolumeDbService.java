package com.cokroktoupadek.beer_ap.service.beer;

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

    private   BoilVolumeRepository boilVolumeRepository;
    @Autowired
    public BoilVolumeDbService(BoilVolumeRepository boilVolumeRepository) {
        this.boilVolumeRepository = boilVolumeRepository;
    }

    BoilVolumeEntity save(BoilVolumeEntity boilVolumeEntity){
        return boilVolumeRepository.save(boilVolumeEntity);
    }

    BoilVolumeEntity findById(Long id) throws Exception {
        return boilVolumeRepository.findById(id).orElseThrow(Exception::new);
    }

   Optional <BoilVolumeEntity> findByUnitAndValue(String unit, Integer value){
        return boilVolumeRepository.findByUnitAndValue(unit,value);
    }



    List<BoilVolumeEntity> findById() {
        return boilVolumeRepository.findAll();
    }

    void deleteById(Long id){
        boilVolumeRepository.deleteById(id);
    }

    void deleteAll(){
        boilVolumeRepository.deleteAll();
    }

    public List<BoilVolumeEntity> findAll() {
        return boilVolumeRepository.findAll();
    }
}

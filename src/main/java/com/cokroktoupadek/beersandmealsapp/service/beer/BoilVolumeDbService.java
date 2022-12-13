package com.cokroktoupadek.beersandmealsapp.service.beer;

import com.cokroktoupadek.beersandmealsapp.domain.entity.beer.BoilVolumeEntity;
import com.cokroktoupadek.beersandmealsapp.repository.beer.BoilVolumeRepository;
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

    public BoilVolumeEntity save(BoilVolumeEntity boilVolumeEntity){
        return boilVolumeRepository.save(boilVolumeEntity);
    }

    public  Optional<BoilVolumeEntity>  findById(Long id)  {
        return boilVolumeRepository.findById(id);
    }

    public  Optional <BoilVolumeEntity> findByUnitAndValue(String unit, Integer value){
        return boilVolumeRepository.findByUnitAndValue(unit,value);
    }


    public   List<BoilVolumeEntity> findById() {
        return boilVolumeRepository.findAll();
    }

    public  void deleteById(Long id){
        boilVolumeRepository.deleteById(id);
    }

    public  void deleteAll(){
        boilVolumeRepository.deleteAll();
    }

    public List<BoilVolumeEntity> findAll() {
        return boilVolumeRepository.findAll();
    }
}

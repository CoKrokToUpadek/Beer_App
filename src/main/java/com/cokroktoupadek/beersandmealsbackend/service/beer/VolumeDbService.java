package com.cokroktoupadek.beersandmealsbackend.service.beer;

import com.cokroktoupadek.beersandmealsbackend.domain.entity.beer.VolumeEntity;
import com.cokroktoupadek.beersandmealsbackend.repository.beer.VolumeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class VolumeDbService {

    private VolumeRepository volumeRepository;
    @Autowired
    public VolumeDbService(VolumeRepository volumeRepository) {
        this.volumeRepository = volumeRepository;
    }

   public VolumeEntity save(VolumeEntity volumeEntity){
        return volumeRepository.save(volumeEntity);
    }


   public Optional<VolumeEntity> findByUnitAndValue(String unit, Integer value){
        return volumeRepository.findByUnitAndValue(unit,value);
    }

    void deleteById(Long id){
        volumeRepository.deleteById(id);
    }

   public void deleteAll(){
        volumeRepository.deleteAll();
    }

    public List<VolumeEntity> findAll() {
        return  volumeRepository.findAll();
    }
}

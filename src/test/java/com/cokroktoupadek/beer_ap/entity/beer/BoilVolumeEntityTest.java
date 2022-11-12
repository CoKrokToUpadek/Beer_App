package com.cokroktoupadek.beer_ap.entity.beer;

import com.cokroktoupadek.beer_ap.domain.entity.beer.BoilVolumeEntity;
import com.cokroktoupadek.beer_ap.domain.entity.beer.VolumeEntity;
import com.cokroktoupadek.beer_ap.repository.beer.BoilVolumeRepository;
import com.cokroktoupadek.beer_ap.repository.beer.VolumeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class BoilVolumeEntityTest {
    @Autowired
    BoilVolumeRepository boilVolumeRepository;

    @Test
    void addBoilVolumeTest(){
        //given
        BoilVolumeEntity boilVolumeEntity=new BoilVolumeEntity(1,"testBoiledVolume");
        //when
        boilVolumeRepository.save(boilVolumeEntity);
        //then
        Long id=boilVolumeEntity.getId();
        Optional<BoilVolumeEntity> fetchedBoilVolumeEntity=boilVolumeRepository.findById(id);
        if (fetchedBoilVolumeEntity.isEmpty()){
            Assertions.fail("fetched value is empty");
        }else {
            Assertions.assertEquals(fetchedBoilVolumeEntity.get().getId(), boilVolumeEntity.getId());
        }
        //cleanup
        boilVolumeRepository.deleteAll();
    }

}
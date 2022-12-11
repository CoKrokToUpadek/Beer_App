package com.cokroktoupadek.beersandmealsapp.h2_test_db.entity.beer;

import com.cokroktoupadek.beersandmealsapp.domain.entity.beer.BoilVolumeEntity;
import com.cokroktoupadek.beersandmealsapp.repository.beer.BoilVolumeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

@SpringBootTest
@TestPropertySource("classpath:application-H2TestDb.properties")
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
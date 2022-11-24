package com.cokroktoupadek.beer_ap.mapper;

import com.cokroktoupadek.beer_ap.domain.entity.beer.BeerEntity;
import com.cokroktoupadek.beer_ap.service.beer.BoilVolumeDbService;
import com.cokroktoupadek.beer_ap.service.beer.VolumeDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BeerEntityFilter {

    @Autowired
    BoilVolumeDbService boilVolumeDbService;

    @Autowired
    VolumeDbService volumeDbService;

   public void beerEntityDuplicateCleanup(BeerEntity beerEntity){
            beerEntity.setBoilVolume(boilVolumeDbService.boilVolumeDuplicateVerifier(beerEntity.getBoilVolume()));
            beerEntity.setVolume(volumeDbService.volumeDuplicateVerifier(beerEntity.getVolume()));
       }
   }



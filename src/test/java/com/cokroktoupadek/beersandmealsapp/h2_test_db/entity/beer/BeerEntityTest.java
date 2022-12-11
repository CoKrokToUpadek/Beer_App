package com.cokroktoupadek.beersandmealsapp.h2_test_db.entity.beer;

import com.cokroktoupadek.beersandmealsapp.domain.entity.beer.*;
import com.cokroktoupadek.beersandmealsapp.repository.beer.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@TestPropertySource("classpath:application-H2TestDb.properties")
class BeerEntityTest {
    @Autowired
    BeerRepository beerRepository;

    @Autowired
    TempRepository tempRepository;

    @Autowired
    AmountRepository amountRepository;

    @Autowired
    VolumeRepository volumeRepository;

    @Autowired
    BoilVolumeRepository boilVolumeRepository;



    @Test
    void addBeerTest(){
        //given
        List<String> pairingList=new ArrayList<>(List.of("pair1","pair2"));
        AmountEntity amountEntity=new AmountEntity(1.0,"testAmount");
        amountRepository.save(amountEntity);
        MaltEntity maltEntity=new MaltEntity("testMalt",amountEntity);
        HopsEntity hopsEntity =new HopsEntity("testName",amountEntity,"testAdd","testAttribute");
        List<MaltEntity> maltEntityList=new ArrayList<>(List.of(maltEntity));
        List<HopsEntity> hopsEntityList=new ArrayList<>(List.of(hopsEntity));
        IngredientsEntity ingredientsEntity=new IngredientsEntity(maltEntityList,hopsEntityList,"testYeast");
        TempEntity tempEntity=new TempEntity(1,"testTemp");
        tempRepository.save(tempEntity);
        MashTempEntity mashTempEntity =new MashTempEntity(tempEntity,1);
        List<MashTempEntity> mashTempEntityList=new ArrayList<>(List.of(mashTempEntity));
        FermentationEntity fermentationEntity=new FermentationEntity(tempEntity);
        MethodEntity methodEntity =new MethodEntity(mashTempEntityList,fermentationEntity);
        VolumeEntity volumeEntity=new VolumeEntity(1,"testUnit");
        volumeRepository.save(volumeEntity);
        BoilVolumeEntity boilVolumeEntity=new BoilVolumeEntity(1,"testUnit");
        boilVolumeRepository.save(boilVolumeEntity);
        BeerEntity beerEntity=new BeerEntity("testDescription","testUrl",1.0,1.0,1,1,1.0,1.0,1.0,1.0,
                volumeEntity, boilVolumeEntity, methodEntity,ingredientsEntity,pairingList,"test-tip","test author");
        //when
        beerRepository.save(beerEntity);
        //then
        Long id=beerEntity.getId();
        Optional<BeerEntity> fetchedBeerEntity = beerRepository.findById(id);
        if (fetchedBeerEntity.isEmpty()){
            Assertions.fail("fetched value is empty");
        }else {
            Assertions.assertEquals(fetchedBeerEntity.get().getId(), beerEntity.getId());
        }
        //cleanup
        beerRepository.deleteAll();
    }

}
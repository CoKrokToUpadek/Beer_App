package com.cokroktoupadek.beer_ap.mapper;

import com.cokroktoupadek.beer_ap.domain.dto.beer.BeerDto;
import com.cokroktoupadek.beer_ap.domain.entity.beer.*;
import com.cokroktoupadek.beer_ap.repository.beer.BeerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class BeerMapperTest {

    @Autowired
    BeerMapper beerMapper;

    @Autowired
    BeerRepository beerRepository;

    @Test
    void mapToBeerDto() {
        List<String> pairingList=new ArrayList<>(List.of("pair1","pair2"));
        AmountEntity amountEntity=new AmountEntity(1.0,"testAmount");
        MaltEntity maltEntity=new MaltEntity("testMalt",amountEntity);
        HopsEntity hopsEntity =new HopsEntity("testName",amountEntity,"testAdd","testAttribute");
        List<MaltEntity> maltEntityList=new ArrayList<>(List.of(maltEntity));
        List<HopsEntity> hopsEntityList=new ArrayList<>(List.of(hopsEntity));
        IngredientsEntity ingredientsEntity=new IngredientsEntity(maltEntityList,hopsEntityList,"testYeast");
        TempEntity tempEntity=new TempEntity(1,"testTemp");
        MashTempEntity mashTempEntity =new MashTempEntity(tempEntity,1);
        MashTempEntity mashTempEntity2 =new MashTempEntity(tempEntity,2);
        List<MashTempEntity> mashTempEntityList=new ArrayList<>(List.of(mashTempEntity,mashTempEntity2));
        FermentationEntity fermentationEntity=new FermentationEntity(new TempEntity(1,"testTemp"));
        MethodEntity methodEntity =new MethodEntity(mashTempEntityList,fermentationEntity);
        VolumeEntity volumeEntity=new VolumeEntity(1,"testUnit");
        BoilVolumeEntity boilVolumeEntity=new BoilVolumeEntity(1,"testUnit");
        BeerEntity beerEntity=new BeerEntity("testDescription","testUrl",1.0,1.0,1,1,1.0,1.0,1.0,1.0,
                volumeEntity, boilVolumeEntity, methodEntity,ingredientsEntity,pairingList,"test-tip","test author");
        beerRepository.save(beerEntity);
        Long id=beerEntity.getId();
        Optional<BeerEntity> fetchedBeerEntity = beerRepository.findById(id);
        //when
        BeerDto beerDto=beerMapper.mapToBeerDto(fetchedBeerEntity.get());
        //then
        assertNotNull(beerDto);
        //dunno what to test here-maybe live app would help

    }

    @Test
    void mapToBeerEntity() {
        List<String> pairingList=new ArrayList<>(List.of("pair1","pair2"));
        AmountEntity amountEntity=new AmountEntity(1.0,"testAmount");
        MaltEntity maltEntity=new MaltEntity("testMalt",amountEntity);
        HopsEntity hopsEntity =new HopsEntity("testName",amountEntity,"testAdd","testAttribute");
        List<MaltEntity> maltEntityList=new ArrayList<>(List.of(maltEntity));
        List<HopsEntity> hopsEntityList=new ArrayList<>(List.of(hopsEntity));
        IngredientsEntity ingredientsEntity=new IngredientsEntity(maltEntityList,hopsEntityList,"testYeast");
        TempEntity tempEntity=new TempEntity(1,"testTemp");
        MashTempEntity mashTempEntity =new MashTempEntity(tempEntity,1);
        MashTempEntity mashTempEntity2 =new MashTempEntity(tempEntity,2);
        List<MashTempEntity> mashTempEntityList=new ArrayList<>(List.of(mashTempEntity,mashTempEntity2));
        FermentationEntity fermentationEntity=new FermentationEntity(new TempEntity(1,"testTemp"));
        MethodEntity methodEntity =new MethodEntity(mashTempEntityList,fermentationEntity);
        VolumeEntity volumeEntity=new VolumeEntity(1,"testUnit");
        BoilVolumeEntity boilVolumeEntity=new BoilVolumeEntity(1,"testUnit");
        BeerEntity beerEntity=new BeerEntity("testDescription","testUrl",1.0,1.0,1,1,1.0,1.0,1.0,1.0,
                volumeEntity, boilVolumeEntity, methodEntity,ingredientsEntity,pairingList,"test-tip","test author");
        beerRepository.save(beerEntity);
        Long id=beerEntity.getId();
        Optional<BeerEntity> fetchedBeerEntity = beerRepository.findById(id);
        BeerDto beerDto=beerMapper.mapToBeerDto(fetchedBeerEntity.get());
        //when
        BeerEntity convertedBeerEntity=beerMapper.mapToBeerEntity(beerDto);
        //then
        assertNotNull(convertedBeerEntity);
        assertEquals(convertedBeerEntity, beerEntity);
    }
}
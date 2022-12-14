package com.cokroktoupadek.beersandmealsapp.h2_test_db.dbservice.beer;
import com.cokroktoupadek.beersandmealsapp.domain.entity.beer.*;
import com.cokroktoupadek.beersandmealsapp.mapper.BeerEntityFilterAndSaver;
import com.cokroktoupadek.beersandmealsapp.service.beer.*;
import org.junit.jupiter.api.*;
import org.mockito.internal.matchers.InstanceOf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@Transactional
@TestPropertySource("classpath:application-H2TestDb.properties")
public class BeerEntityManipulatorAndFilterTest {
    @Autowired
    BeerEntityManipulatorDbService beerEntityManipulatorDbService;
    @Autowired
    VolumeDbService volumeDbService;
    @Autowired
    BoilVolumeDbService boilVolumeDbService;
    @Autowired
    TempDbService tempDbService;
    @Autowired
    AmountDbService amountDbService;
    @Autowired
    BeerDbService beerDbService;

    @Autowired
    BeerEntityFilterAndSaver beerEntityFilterAndSaver;

    @AfterEach
    void deleteAll(){
        volumeDbService.deleteAll();
        boilVolumeDbService.deleteAll();
        tempDbService.deleteAll();
        amountDbService.deleteAll();
    }

    @Test
    void amountDuplicateVerifierEntityPresent() {
        //given
        AmountEntity amountEntity=new AmountEntity(1d,"unit");
        amountDbService.save(amountEntity);
        //when
        AmountEntity fetchedAmount=beerEntityManipulatorDbService.amountDuplicateVerifier(amountEntity);
        //then
        Assertions.assertEquals(amountEntity,fetchedAmount);
    }

    @Test
    void amountDuplicateVerifierEntityNotPresent() {
        //given
        AmountEntity amountEntity=new AmountEntity(1d,"unit");
        //when
        AmountEntity fetchedAmount=beerEntityManipulatorDbService.amountDuplicateVerifier(amountEntity);
        //then
        Assertions.assertEquals(fetchedAmount,amountDbService.findByValueAndUnit(1d,"unit").get());

    }


    @Test
    void boilVolumeDuplicateVerifierEntityPresent() {
        //given
        BoilVolumeEntity boilVolumeEntity=new BoilVolumeEntity(1,"unit");
        boilVolumeDbService.save(boilVolumeEntity);
        //when
        BoilVolumeEntity fetchedBoilVolume=beerEntityManipulatorDbService.boilVolumeDuplicateVerifier(boilVolumeEntity);
        //then
        Assertions.assertEquals(boilVolumeEntity,fetchedBoilVolume);
    }

    @Test
    void boilVolumeDuplicateVerifierEntityNotPresent() {
        //given
        BoilVolumeEntity boilVolumeEntity=new BoilVolumeEntity(1,"unit");
        //when
        BoilVolumeEntity fetchedBoilVolume=beerEntityManipulatorDbService.boilVolumeDuplicateVerifier(boilVolumeEntity);
        //then
        Assertions.assertEquals(fetchedBoilVolume,boilVolumeDbService.findByUnitAndValue("unit",1).get());
    }

    @Test
    void tempDuplicateVerifierEntityPresent() {
        //given
        TempEntity tempEntity=new TempEntity(1,"unit");
        tempDbService.save(tempEntity);
        //when
        TempEntity fetchedTempEntity=beerEntityManipulatorDbService.tempDuplicateVerifier(tempEntity);
        //then
        Assertions.assertEquals(tempEntity,fetchedTempEntity);
    }

    @Test
    void tempDuplicateVerifierEntityNotPresent() {
        //given
        TempEntity tempEntity=new TempEntity(1,"unit");
        //when
        TempEntity fetchedTempEntity=beerEntityManipulatorDbService.tempDuplicateVerifier(tempEntity);
        //then
        Assertions.assertEquals(fetchedTempEntity,tempDbService.findByValueAndUnit(1,"unit").get());
    }

    @Test
    void volumeDuplicateVerifierEntityPresent() {
        //given
        VolumeEntity volumeEntity=new VolumeEntity(1,"unit");
        volumeDbService.save(volumeEntity);
        //when
        VolumeEntity fetchedVolume=beerEntityManipulatorDbService.volumeDuplicateVerifier(volumeEntity);
        //then
        Assertions.assertEquals(volumeEntity,fetchedVolume);
      try{

      }catch (InputMismatchException e){

      }
    }

    @Test
    void beerExistenceInDbVerifierEntityNotPresent() {
        //given
        VolumeEntity volumeEntity=new VolumeEntity(1,"unit");
        //when
        VolumeEntity fetchedVolume=beerEntityManipulatorDbService.volumeDuplicateVerifier(volumeEntity);
        //then
        Assertions.assertEquals(fetchedVolume,volumeDbService.findByUnitAndValue("unit",1).get());
    }


}

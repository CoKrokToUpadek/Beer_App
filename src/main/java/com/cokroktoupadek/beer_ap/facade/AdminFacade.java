package com.cokroktoupadek.beer_ap.facade;

import com.cokroktoupadek.beer_ap.client.BeerClient;
import com.cokroktoupadek.beer_ap.domain.dto.beer.BeerDto;
import com.cokroktoupadek.beer_ap.domain.entity.beer.BeerEntity;
import com.cokroktoupadek.beer_ap.errorhandlers.BeerDbIsEmptyException;
import com.cokroktoupadek.beer_ap.errorhandlers.BeerNotFoundException;
import com.cokroktoupadek.beer_ap.mapper.BeerMapper;
import com.cokroktoupadek.beer_ap.mapper.BeerEntityFilterAndSaver;
import com.cokroktoupadek.beer_ap.service.beer.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AdminFacade {


    private BeerDbService beerDbService;
    private BeerMapper beerMapper;
    private BeerClient beerClient;
    private  BeerEntityFilterAndSaver beerEntityFilter;
    private BeerEntityManipulatorDbService beerEntityManipulatorDbService;
    @Autowired
    public AdminFacade(BeerDbService beerDbService, BeerMapper beerMapper, BeerClient beerClient, BeerEntityFilterAndSaver beerEntityFilter,BeerEntityManipulatorDbService beerEntityManipulatorDbService) {
        this.beerDbService = beerDbService;
        this.beerMapper = beerMapper;
        this.beerClient = beerClient;
        this.beerEntityFilter = beerEntityFilter;
        this.beerEntityManipulatorDbService=beerEntityManipulatorDbService;
    }

    public String updateDbFacade(){
        List<BeerDto> beerDtoList=beerClient.getBeerDtoList();
        List<BeerEntity> beerEntities=beerMapper.mapToBeerEntityList(beerDtoList);
        for (BeerEntity beerEntity:beerEntities){
            beerEntityFilter.beerEntitySaver(beerEntity);
        }
        return "beer list updated successfully";
    }

    public String tempSingleSave(int beerNo){
        List<BeerDto> beerDtoList=beerClient.getBeerDto(beerNo);
        List<BeerEntity> beerEntities=beerMapper.mapToBeerEntityList(beerDtoList);
        beerEntities.forEach(beerDbService::save);
        return "beer list updated successfully";
    }

    public String deleteSingleBeer(String name) throws BeerNotFoundException{
        if (beerDbService.findByName(name).isEmpty()){
            throw new BeerNotFoundException();
        }
        beerEntityManipulatorDbService.beerEntityDeleter(name);
        beerEntityManipulatorDbService.entitiesWithEmptyRelationsCleaner();
        return "beer was deleted successfully";
    }

    public String deleteAllBeers() throws BeerDbIsEmptyException {
        List<BeerEntity> beerEntityList= beerDbService.findAll();
        if(beerEntityList.isEmpty()){
            throw new BeerDbIsEmptyException();
        }
        beerEntityManipulatorDbService.allBeerEntitiesDeleter(beerEntityList.stream().map(BeerEntity::getName).collect(Collectors.toList()));
        beerEntityManipulatorDbService.entitiesWithEmptyRelationsCleaner();
        return "all beers has been deleted successfully";
    }

}

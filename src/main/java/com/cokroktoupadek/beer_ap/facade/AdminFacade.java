package com.cokroktoupadek.beer_ap.facade;

import com.cokroktoupadek.beer_ap.client.BeerClient;
import com.cokroktoupadek.beer_ap.domain.dto.beer.BeerDto;
import com.cokroktoupadek.beer_ap.domain.entity.beer.BeerEntity;
import com.cokroktoupadek.beer_ap.mapper.BeerMapper;
import com.cokroktoupadek.beer_ap.mapper.BeerEntityFilterAndSaver;
import com.cokroktoupadek.beer_ap.service.beer.BeerDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AdminFacade {

    public BeerDbService beerDbService;
    public BeerMapper beerMapper;
    public BeerClient beerClient;
    public BeerEntityFilterAndSaver beerEntityFilter;
    @Autowired
    public AdminFacade(BeerDbService beerDbService, BeerMapper beerMapper, BeerClient beerClient, BeerEntityFilterAndSaver beerEntityFilter) {
        this.beerDbService = beerDbService;
        this.beerMapper = beerMapper;
        this.beerClient = beerClient;
        this.beerEntityFilter = beerEntityFilter;
    }

    public String updateDbFacade(){
        List<BeerDto> beerDtoList=beerClient.getBeerDtoList();
        List<BeerEntity> beerEntities=beerMapper.mapToBeerEntityList(beerDtoList);
        for (BeerEntity beerEntity:beerEntities){
            beerEntityFilter.beerEntityCleanupAndSave(beerEntity);
            beerDbService.save(beerEntity);
        }
        return "beer list updated successfully";
    }

    public String tempSingleSave(int beerNo){
        List<BeerDto> beerDtoList=beerClient.getBeerDto(beerNo);
        List<BeerEntity> beerEntities=beerMapper.mapToBeerEntityList(beerDtoList);
        beerEntities.forEach(beerDbService::save);
        return "beer list updated successfully";
    }

}

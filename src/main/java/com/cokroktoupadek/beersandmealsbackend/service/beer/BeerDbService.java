package com.cokroktoupadek.beersandmealsbackend.service.beer;

import com.cokroktoupadek.beersandmealsbackend.domain.entity.beer.BeerEntity;
import com.cokroktoupadek.beersandmealsbackend.repository.beer.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BeerDbService {

    private BeerRepository beerRepository;
    @Autowired
    public BeerDbService(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    public BeerEntity save(BeerEntity beerEntity) {
        return beerRepository.save(beerEntity);
    }

    public Optional<BeerEntity> findById(Long id)  {
        return beerRepository.findById(id);
    }

    public List<BeerEntity> findAll() {
        return beerRepository.findAll();
    }

    public void deleteById(Long id) {
        beerRepository.deleteById(id);
    }

    public void deleteAll() {
        beerRepository.deleteAll();
    }

    public Optional<BeerEntity> findByName(String name) {
      return beerRepository.findByName(name);
    }
}

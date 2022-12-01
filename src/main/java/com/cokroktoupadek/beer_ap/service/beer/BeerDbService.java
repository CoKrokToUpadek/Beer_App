package com.cokroktoupadek.beer_ap.service.beer;

import com.cokroktoupadek.beer_ap.domain.entity.beer.BeerEntity;
import com.cokroktoupadek.beer_ap.repository.beer.*;
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
    @Autowired
    BeerRepository beerRepository;

    public BeerEntity save(BeerEntity beerEntity) {
        return beerRepository.save(beerEntity);
    }

    public BeerEntity findById(Long id) throws Exception {
        return beerRepository.findById(id).orElseThrow(Exception::new);
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

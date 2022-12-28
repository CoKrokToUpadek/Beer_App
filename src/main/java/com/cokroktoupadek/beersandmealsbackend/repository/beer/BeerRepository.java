package com.cokroktoupadek.beersandmealsbackend.repository.beer;

import com.cokroktoupadek.beersandmealsbackend.domain.entity.beer.BeerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface BeerRepository extends CrudRepository<BeerEntity, Long>{
    @Override
    List<BeerEntity> findAll();

    @Override
    Optional<BeerEntity> findById(Long id);

    Optional<BeerEntity> findByName(String name);

    @Override
    BeerEntity save(BeerEntity beerEntity);

    @Override
    void deleteById(Long id);

    @Override
    void deleteAll();


}



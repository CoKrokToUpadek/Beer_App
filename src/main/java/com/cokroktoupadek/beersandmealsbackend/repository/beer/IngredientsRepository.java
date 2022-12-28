package com.cokroktoupadek.beersandmealsbackend.repository.beer;

import com.cokroktoupadek.beersandmealsbackend.domain.entity.beer.IngredientsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface IngredientsRepository extends CrudRepository<IngredientsEntity, Long>{
    @Override
    List<IngredientsEntity> findAll();

    @Override
    Optional<IngredientsEntity> findById(Long id);

    @Override
    IngredientsEntity save(IngredientsEntity productEntity);

    @Override
    void deleteById(Long id);

    @Override
    void deleteAll();
}




package com.cokroktoupadek.beersandmealsapp.repository.beer;

import com.cokroktoupadek.beersandmealsapp.domain.entity.beer.FermentationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface FermentationRepository extends CrudRepository<FermentationEntity, Long>{
    @Override
    List<FermentationEntity> findAll();

    @Override
    Optional<FermentationEntity> findById(Long id);

    @Override
    FermentationEntity save(FermentationEntity fermentationEntity);

    @Override
    void deleteById(Long id);

    @Override
    void deleteAll();
}



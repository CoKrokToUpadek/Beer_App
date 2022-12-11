package com.cokroktoupadek.beersandmealsapp.repository.beer;


import com.cokroktoupadek.beersandmealsapp.domain.entity.beer.MaltEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface MaltRepository extends CrudRepository<MaltEntity, Long> {
    @Override
    List<MaltEntity> findAll();

    @Override
    Optional<MaltEntity> findById(Long id);

    @Override
    MaltEntity save(MaltEntity maltEntity);

    @Override
    void deleteById(Long id);

    @Override
    void deleteAll();
}

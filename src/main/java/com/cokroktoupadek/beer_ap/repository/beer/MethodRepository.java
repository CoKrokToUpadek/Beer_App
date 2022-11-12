package com.cokroktoupadek.beer_ap.repository.beer;

import com.cokroktoupadek.beer_ap.domain.entity.beer.MethodEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface MethodRepository extends CrudRepository<MethodEntity, Long> {
    @Override
    List<MethodEntity> findAll();

    @Override
    Optional<MethodEntity> findById(Long id);

    @Override
    MethodEntity save(MethodEntity methodEntity);

    @Override
    void deleteById(Long id);

    @Override
    void deleteAll();
}


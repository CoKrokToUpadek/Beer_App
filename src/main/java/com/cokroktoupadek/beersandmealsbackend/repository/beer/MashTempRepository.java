package com.cokroktoupadek.beersandmealsbackend.repository.beer;

import com.cokroktoupadek.beersandmealsbackend.domain.entity.beer.MashTempEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface MashTempRepository extends CrudRepository<MashTempEntity, Long> {
    @Override
    List<MashTempEntity> findAll();

    @Override
    Optional<MashTempEntity> findById(Long id);

    @Override
    MashTempEntity save(MashTempEntity mashTempEntity);

    @Override
    void deleteById(Long id);

    @Override
    void deleteAll();
}



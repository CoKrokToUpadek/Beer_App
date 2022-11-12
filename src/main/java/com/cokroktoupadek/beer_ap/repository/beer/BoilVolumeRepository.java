package com.cokroktoupadek.beer_ap.repository.beer;


import com.cokroktoupadek.beer_ap.domain.entity.beer.BoilVolumeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface BoilVolumeRepository extends CrudRepository<BoilVolumeEntity, Long>{
    @Override
    List<BoilVolumeEntity> findAll();

    @Override
    Optional<BoilVolumeEntity> findById(Long id);

    @Override
    BoilVolumeEntity save(BoilVolumeEntity boilVolumeEntity);

    @Override
    void deleteById(Long id);

    @Override
    void deleteAll();
}


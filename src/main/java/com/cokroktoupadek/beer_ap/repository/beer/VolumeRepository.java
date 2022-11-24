package com.cokroktoupadek.beer_ap.repository.beer;

import com.cokroktoupadek.beer_ap.domain.entity.beer.BoilVolumeEntity;
import com.cokroktoupadek.beer_ap.domain.entity.beer.TempEntity;
import com.cokroktoupadek.beer_ap.domain.entity.beer.VolumeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface VolumeRepository extends CrudRepository<VolumeEntity, Long> {
    @Override
    List<VolumeEntity> findAll();

    @Override
    Optional<VolumeEntity> findById(Long id);

    @Override
    VolumeEntity save(VolumeEntity volumeEntity);

    Optional<VolumeEntity> findByUnitAndValue(String unit, Integer value);

    @Override
    void deleteById(Long id);

    @Override
    void deleteAll();
}


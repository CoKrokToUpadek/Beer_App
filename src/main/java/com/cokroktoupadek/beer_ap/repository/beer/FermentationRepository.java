package com.cokroktoupadek.beer_ap.repository.beer;

import com.cokroktoupadek.beer_ap.domain.entity.beer.FermentationEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
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

    @Query(nativeQuery = true)
    List<FermentationEntity> getFermentationDuplicates(@Param("TEMPUNIT") String unit, @Param("TEMPVALUE") Integer value);

    @Override
    FermentationEntity save(FermentationEntity fermentationEntity);

    @Override
    void deleteById(Long id);

    @Override
    void deleteAll();
}



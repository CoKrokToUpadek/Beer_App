package com.cokroktoupadek.beer_ap.repository.beer;

import com.cokroktoupadek.beer_ap.domain.entity.beer.AmountEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface AmountRepository extends CrudRepository<AmountEntity, Long> {
    @Override
    List<AmountEntity> findAll();

    @Override
    Optional<AmountEntity> findById(Long id);

    @Override
    AmountEntity save(AmountEntity amountEntity);

    @Override
    void deleteById(Long id);

    @Override
    void deleteAll();
}

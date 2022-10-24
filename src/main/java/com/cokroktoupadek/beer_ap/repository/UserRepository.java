package com.cokroktoupadek.beer_ap.repository;

import com.cokroktoupadek.beer_ap.domain.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    Optional<UserEntity> findById(Long id);
    void deleteById(Long id);
}

package com.cokroktoupadek.beersandmealsapp.repository.user;

import com.cokroktoupadek.beersandmealsapp.domain.entity.user.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    @Override
    List<UserEntity> findAll();

    @Override
    Optional<UserEntity> findById(Long id);

    Optional<UserEntity> findByLogin(String login);

    Optional<UserEntity> findByEmail(String email);

    @Override
    UserEntity save(UserEntity userEntity);

    @Override
    void deleteById(Long id);
}

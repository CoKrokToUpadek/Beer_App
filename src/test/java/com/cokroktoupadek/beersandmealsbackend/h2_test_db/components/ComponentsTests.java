package com.cokroktoupadek.beersandmealsbackend.h2_test_db.components;

import com.cokroktoupadek.beersandmealsbackend.domain.entity.user.UserEntity;
import com.cokroktoupadek.beersandmealsbackend.repository.user.UserRepository;
import com.cokroktoupadek.beersandmealsbackend.service.user.BeersAndMealsUserDetailsService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
@TestPropertySource("classpath:application-H2TestDb.properties")
public class ComponentsTests {

    @Autowired
    BeersAndMealsUserDetailsService service;

    @Autowired
    UserRepository userRepository;
    @Test
    void assertNullUserTest(){
        //given
        //when
        //then
         assertThrows(UsernameNotFoundException.class, () -> {
             service.loadUserByUsername("not found");
         });

    }

    @Test
    void assertFoundEmailTest(){
        //given
       UserEntity userEntity=new UserEntity("firstname","lastname","1","testemail","1","1","1", LocalDate.now());
       userRepository.save(userEntity);
        //when
       Optional<UserEntity> entity= userRepository.findByLogin("1");
        //then
       assertTrue(entity.isPresent());
    }

}

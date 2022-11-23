package com.cokroktoupadek.beer_ap.components;

import com.cokroktoupadek.beer_ap.domain.entity.user.UserEntity;
import com.cokroktoupadek.beer_ap.repository.user.UserRepository;
import com.cokroktoupadek.beer_ap.service.user.BeerUserDetailsService;
import org.apache.catalina.User;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
public class ComponentsTests {



    BeerUserDetailsService service=new BeerUserDetailsService();

    @Autowired
    UserRepository userRepository;
    @Test
    void assertNullUserTest(){
        service.loadUserByUsername("sdadsa");
    }


    @Test
    void assertFoundEmailTest(){
       UserEntity userEntity=new UserEntity("firstname","lastname","1","testemail","1","1","1", LocalDate.now());
       userRepository.save(userEntity);
       Optional<UserEntity> entity= userRepository.findByLogin("1");
       assertTrue(entity.isPresent());
    }

}

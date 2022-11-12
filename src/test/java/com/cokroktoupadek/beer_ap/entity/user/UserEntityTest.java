package com.cokroktoupadek.beer_ap.entity.user;

import com.cokroktoupadek.beer_ap.domain.entity.user.UserEntity;
import com.cokroktoupadek.beer_ap.repository.user.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserEntityTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testCreateUser() {
        //Given
        UserEntity user = new UserEntity(null, "test", "test","test","test@test.cm","test","test",LocalDate.now(),1L,1,new ArrayList<>());

        //When
        userRepository.save(user);

        //Then
        Long userId = user.getId();
        Optional<UserEntity> readUser = userRepository.findById(userId);
        if (readUser.isEmpty()){
            Assertions.fail("fetched value is empty");
        }else {
            assertEquals(user.getId(), readUser.get().getId());
        }

        //Cleanup
        userRepository.deleteById(userId);
    }

}
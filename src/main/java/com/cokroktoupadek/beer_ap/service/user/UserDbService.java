package com.cokroktoupadek.beer_ap.service.user;

import com.cokroktoupadek.beer_ap.domain.entity.user.UserEntity;
import com.cokroktoupadek.beer_ap.repository.user.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserDbService {

    private UserRepository userRepository;
   @Autowired
    public UserDbService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity save(UserEntity userEntity){
        return userRepository.save(userEntity);
    }

  public   UserEntity findById(Long id) throws Exception {
        return userRepository.findById(id).orElseThrow(Exception::new);
    }

   public Optional<UserEntity> findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public  Optional<UserEntity> findByEmail(String email)  {
        return userRepository.findByEmail(email);
    }


    List<UserEntity> findById() {
        return userRepository.findAll();
    }

    void DeleteById(Long id){
        userRepository.deleteById(id);
    }

    void DeleteAll(){
        userRepository.deleteAll();
    }

}

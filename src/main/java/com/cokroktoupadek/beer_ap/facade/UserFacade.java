package com.cokroktoupadek.beer_ap.facade;

import com.cokroktoupadek.beer_ap.domain.dto.user.CreatedUserDto;
import com.cokroktoupadek.beer_ap.domain.entity.user.UserEntity;
import com.cokroktoupadek.beer_ap.errorhandlers.UserCreationException;
import com.cokroktoupadek.beer_ap.mapper.BeerMapper;
import com.cokroktoupadek.beer_ap.service.user.UserDbService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.After;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class UserFacade {


    public UserDbService userDbService;
    public BeerMapper beerMapper;


    @Autowired
    public UserFacade(UserDbService userDbService, BeerMapper beerMapper) {
        this.userDbService = userDbService;
        this.beerMapper = beerMapper;
    }

    public String createUserFacade(CreatedUserDto userDto) {

        boolean wasLoginTaken=true;
        boolean wasEmailTaken=true;
        UserEntity entity;
        try {
           userDbService.findByLogin(userDto.getLogin());
        }catch (Exception e){
            wasLoginTaken=false;
        }
        try {
            userDbService.findByEmail(userDto.getEmail());
        }catch (Exception e){
            wasEmailTaken=false;
        }
        if (wasLoginTaken){
            return UserCreationException.ERR_LOGIN_TAKEN;
        }else if(wasEmailTaken){
            return UserCreationException.ERR_EMAIL_TAKEN;
        }else if(Stream.of(userDto.getAddress(), userDto.getEmail(), userDto.getLogin(), userDto.getPassword(),
                userDto.getFirstName(),userDto.getLastName()).anyMatch(Objects::isNull)){
           return UserCreationException.ERR_MISSING_INFORMATION;
        }else {
             entity=beerMapper.mapNewUserEntity(userDto);
             userDbService.save(entity);
             return "user was created successfully";
        }

    }

}

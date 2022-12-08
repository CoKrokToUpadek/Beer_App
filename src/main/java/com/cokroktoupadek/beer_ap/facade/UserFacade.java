package com.cokroktoupadek.beer_ap.facade;

import com.cokroktoupadek.beer_ap.domain.dto.beer.BeerDto;
import com.cokroktoupadek.beer_ap.domain.dto.user.CreatedUserDto;
import com.cokroktoupadek.beer_ap.domain.entity.beer.BeerEntity;
import com.cokroktoupadek.beer_ap.domain.entity.user.UserEntity;
import com.cokroktoupadek.beer_ap.errorhandlers.BeerDbIsEmptyException;
import com.cokroktoupadek.beer_ap.errorhandlers.UserCreationException;
import com.cokroktoupadek.beer_ap.mapper.BeerMapper;
import com.cokroktoupadek.beer_ap.service.beer.BeerDbService;
import com.cokroktoupadek.beer_ap.service.user.UserDbService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.After;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.PushBuilder;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class UserFacade {


    private UserDbService userDbService;

    private BeerDbService beerDbService;

    private BeerMapper beerMapper;

    @Autowired
    public UserFacade(UserDbService userDbService, BeerDbService beerDbService, BeerMapper beerMapper) {
        this.userDbService = userDbService;
        this.beerDbService = beerDbService;
        this.beerMapper = beerMapper;
    }

    public String createUserFacade(CreatedUserDto userDto) {

        UserEntity entity;
        if(userDbService.findByLogin(userDto.getLogin()).isPresent()) {
            return UserCreationException.ERR_LOGIN_TAKEN;
        }
       if (userDbService.findByEmail(userDto.getEmail()).isPresent()) {
           return UserCreationException.ERR_EMAIL_TAKEN;
        }
       if(Stream.of(userDto.getAddress(), userDto.getEmail(), userDto.getLogin(), userDto.getPassword(),
                userDto.getFirstName(),userDto.getLastName()).anyMatch(Objects::isNull)){
           return UserCreationException.ERR_MISSING_INFORMATION;
        }else {
             entity=beerMapper.mapNewUserEntity(userDto);
             userDbService.save(entity);
             return "user was created successfully";
        }

    }

    public List<BeerDto> getBeerList() throws BeerDbIsEmptyException {
      List<BeerEntity> beerEntities= beerDbService.findAll();
      if (!beerEntities.isEmpty()){
          return beerMapper.mapToBeerDtoList(beerEntities);
      }else {
          throw new BeerDbIsEmptyException();
      }
    }


}

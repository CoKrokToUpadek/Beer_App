package com.cokroktoupadek.beer_ap.service;

import com.cokroktoupadek.beer_ap.client.config.BeerUserDetails;
import com.cokroktoupadek.beer_ap.domain.entity.user.UserEntity;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;


@Service
public class BeerUserDetailsService implements UserDetailsService {

    @Autowired
    UserDbService userDbService;

    @Override
    public BeerUserDetails loadUserByUsername(String username) {
        try {
          UserEntity  user = userDbService.findCartByLogin(username);
          return new BeerUserDetails(user);
        } catch (AuthenticationException e) {
            throw new UsernameNotFoundException("User not found");
        }
    }
}

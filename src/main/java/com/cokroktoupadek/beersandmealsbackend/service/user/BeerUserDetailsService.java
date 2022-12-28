package com.cokroktoupadek.beersandmealsbackend.service.user;

import com.cokroktoupadek.beersandmealsbackend.client.config.AppUserDetails;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class BeerUserDetailsService implements UserDetailsService {

    private UserDbService userDbService;
    @Autowired
    public BeerUserDetailsService(UserDbService userDbService) {
        this.userDbService = userDbService;
    }

    @Override
    public AppUserDetails loadUserByUsername(String username) {
        if (userDbService.findByLogin(username).isPresent()) {
            return new AppUserDetails(userDbService.findByLogin(username).get());
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}

package com.cokroktoupadek.beer_ap.service;

import com.cokroktoupadek.beer_ap.domain.entity.user.UserEntity;
import com.cokroktoupadek.beer_ap.errorhandlers.UserNotFoundException;
import com.cokroktoupadek.beer_ap.repository.user.UserRepository;

import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;

@Service
@RequiredArgsConstructor
public class UserDbService {
    @Autowired
    private final UserRepository userRepository;

    public UserEntity findUserById(final Long id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }
    public UserEntity findCartByLogin(final String login) throws AuthenticationException {
        return userRepository.findByLogin(login).orElseThrow(AuthenticationException::new);
    }

}

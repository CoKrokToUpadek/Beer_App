package com.cokroktoupadek.beersandmealsapp.domain.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreatedUserDto {

   private String firstName;
   private String lastName;
   private String address;
   private String email;
   private String login;
   private String password;
}

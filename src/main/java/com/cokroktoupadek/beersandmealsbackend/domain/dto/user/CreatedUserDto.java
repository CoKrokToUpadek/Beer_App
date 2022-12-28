package com.cokroktoupadek.beersandmealsbackend.domain.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreatedUserDto {

   private String firstName;
   private String lastName;
   private String address;
   private String email;
   private String login;
   private String password;
}

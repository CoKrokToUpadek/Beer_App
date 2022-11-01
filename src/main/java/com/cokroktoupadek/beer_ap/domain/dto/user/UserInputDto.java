package com.cokroktoupadek.beer_ap.domain.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class UserInputDto {
   private Long id;
   private String firstName;
   private String lastName;
   private String address;
   private String email;
   private String login;
   private String password;
   private LocalDate creationDate;
}

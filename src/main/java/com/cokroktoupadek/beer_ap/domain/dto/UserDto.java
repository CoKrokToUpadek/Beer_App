package com.cokroktoupadek.beer_ap.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class UserDto {
   private Long id;
   private String username;
   private int status;
   private int userKey;
}

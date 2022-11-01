package com.cokroktoupadek.beer_ap.domain.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class UserDto {
   private Long id;
   private String username;
   private Integer status;
   private Integer userKey;
}

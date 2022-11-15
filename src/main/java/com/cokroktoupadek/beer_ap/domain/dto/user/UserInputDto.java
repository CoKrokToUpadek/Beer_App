package com.cokroktoupadek.beer_ap.domain.dto.user;

import com.cokroktoupadek.beer_ap.domain.dto.beer.BeerDto;
import com.cokroktoupadek.beer_ap.domain.entity.beer.BeerEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import javax.persistence.Column;
import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class UserInputDto {
   private Long id;
   private String firstName;
   private String lastName;
   private String address;
   private String email;
   private String login;
   private String userRole;
   private String password;
   private LocalDate creationDate;
   private List<BeerDto> favouredBeers;
}

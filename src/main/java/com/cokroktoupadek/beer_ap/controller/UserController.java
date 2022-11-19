package com.cokroktoupadek.beer_ap.controller;


import com.cokroktoupadek.beer_ap.domain.dto.beer.BeerDto;
import com.cokroktoupadek.beer_ap.domain.dto.user.UserDto;
import com.cokroktoupadek.beer_ap.domain.dto.user.UserInputDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

  //  @Secured({"ROLE_ANONYMOUS"})
    @PostMapping("/create_user")
    public ResponseEntity<UserDto> createUser(@RequestBody UserInputDto userInputDto){
        return ResponseEntity.ok(new UserDto(1L, userInputDto.getLogin(), 1,666));
    }

    @Secured({"ROLE_ADMIN","ROLE_USER"})
    @PostMapping("/Add_beer_to_favorite")
    public ResponseEntity<Void> addBeerToFavorite(@RequestParam Integer beerId){
        return ResponseEntity.ok().build();
    }

    @Secured({"ROLE_ADMIN","ROLE_USER"})
    @GetMapping("/Get_my_favorite_beers")
    public ResponseEntity<List<BeerDto>> getMyFavoriteBeers(){
        return ResponseEntity.ok(new ArrayList<>());
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("/block_user")
    public ResponseEntity<String> blockUserById(@RequestParam Long id){
        return ResponseEntity.ok("User with requested ID was blocked");
    }

///////////////////////////////test endpoints////////////////////////////////////////////////


    @GetMapping("/dummy")
    public ResponseEntity<String> dummy(@CurrentSecurityContext SecurityContext context){
        return ResponseEntity.ok(context.getAuthentication().getName()+ " have access to open method");
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/dummy_for_authorised")
    public ResponseEntity<String> dummy2(@CurrentSecurityContext SecurityContext context){

        return ResponseEntity.ok(context.getAuthentication().getName()+ " have access to protected method");
    }
}

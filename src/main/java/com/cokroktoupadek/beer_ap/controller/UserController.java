package com.cokroktoupadek.beer_ap.controller;


import com.cokroktoupadek.beer_ap.domain.dto.beer.BeerDto;
import com.cokroktoupadek.beer_ap.domain.dto.user.CreatedUserDto;
import com.cokroktoupadek.beer_ap.facade.UserFacade;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/beers")
@AllArgsConstructor
public class UserController {

    UserFacade userFacade;

    //  @Secured({"ROLE_ANONYMOUS"})
    @PostMapping("/create_user")//ok
    public ResponseEntity<String> createUser(@RequestBody CreatedUserDto userInputDto) {
        return ResponseEntity.ok(userFacade.createUserFacade(userInputDto));
    }

    @Secured({"ROLE_ADMIN","ROLE_USER"})
    @GetMapping("/get_beers")
    public ResponseEntity<List<BeerDto>> getBeerList() {
        List<BeerDto> beerDtoList = new ArrayList<>();
        return ResponseEntity.ok(beerDtoList);
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

}

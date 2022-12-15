package com.cokroktoupadek.beersandmealsapp.controller;


import com.cokroktoupadek.beersandmealsapp.domain.dto.beer.BeerDto;
import com.cokroktoupadek.beersandmealsapp.domain.dto.meals.program.MealDto;
import com.cokroktoupadek.beersandmealsapp.domain.dto.user.CreatedUserDto;
import com.cokroktoupadek.beersandmealsapp.errorhandlers.BeerDbIsEmptyException;
import com.cokroktoupadek.beersandmealsapp.errorhandlers.MealDbIsEmptyException;
import com.cokroktoupadek.beersandmealsapp.facade.UserFacade;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    UserFacade userFacade;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @PostMapping("/create_user")
    public ResponseEntity<String> createUser(@RequestBody CreatedUserDto userInputDto) {
        return ResponseEntity.ok(userFacade.createUser(userInputDto));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @GetMapping("/get_beers")
    public ResponseEntity<List<BeerDto>> getBeerList() throws BeerDbIsEmptyException {
        return ResponseEntity.ok(userFacade.getBeerList());
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @GetMapping("/get_meals")
    public ResponseEntity<List<MealDto>> getMealList() throws MealDbIsEmptyException {
        return ResponseEntity.ok(userFacade.getMealList());
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @PostMapping("/Add_beer_to_favorite")
    public ResponseEntity<String> addBeerToFavorite(@CurrentSecurityContext SecurityContext context,@RequestParam String beerName){
        return ResponseEntity.ok(userFacade.addBeerToFavorite(beerName,context.getAuthentication().getName()));
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @DeleteMapping("/remove_beer_from_favorite")
    public ResponseEntity<String> removeBeerFromFavorite(@CurrentSecurityContext SecurityContext context,@RequestParam String beerName){
        return ResponseEntity.ok(userFacade.removeBeerFromFavorite(beerName,context.getAuthentication().getName()));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @GetMapping("/Get_my_favorite_beers")
    public ResponseEntity<List<BeerDto>> getMyFavoriteBeers(@CurrentSecurityContext SecurityContext context){
        return ResponseEntity.ok(userFacade.getBeerFavoriteList(context.getAuthentication().getName()));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @PostMapping("/Add_meal_to_favorite")
    public ResponseEntity<String> addMealToFavorite(@CurrentSecurityContext SecurityContext context,@RequestParam String mealName){
        return ResponseEntity.ok(userFacade.addMealToFavorite(mealName,context.getAuthentication().getName()));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @DeleteMapping("/remove_meal_from_favorite")
    public ResponseEntity<String> removeMealFromFavorite(@CurrentSecurityContext SecurityContext context,@RequestParam String mealName){
        return ResponseEntity.ok(userFacade.removeMealFromFavorite(mealName,context.getAuthentication().getName()));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @GetMapping("/Get_my_favorite_meals")
    public ResponseEntity<List<MealDto>> getMyFavoriteMeals(@CurrentSecurityContext SecurityContext context){
        return ResponseEntity.ok(userFacade.getMealFavoriteList(context.getAuthentication().getName()));
    }

}

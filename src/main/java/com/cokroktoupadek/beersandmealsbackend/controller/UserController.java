package com.cokroktoupadek.beersandmealsbackend.controller;


import com.cokroktoupadek.beersandmealsbackend.domain.dto.beer.BeerDto;
import com.cokroktoupadek.beersandmealsbackend.domain.dto.meals.program.MealDto;
import com.cokroktoupadek.beersandmealsbackend.domain.dto.user.CreatedUserDto;
import com.cokroktoupadek.beersandmealsbackend.domain.dto.user.UserCredentialsDto;
import com.cokroktoupadek.beersandmealsbackend.errorhandlers.BeerDbIsEmptyException;
import com.cokroktoupadek.beersandmealsbackend.errorhandlers.MealDbIsEmptyException;
import com.cokroktoupadek.beersandmealsbackend.facade.UserFacade;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    UserFacade userFacade;
    ///////////////////////////////administrative////////////////////////////////////////////////

//  private final TokenService tokenService;

//    @PostMapping("/token")
//    public String token(Authentication authentication){
//        String token=tokenService.generateToken(authentication);
//        return token;
//    }
    @GetMapping("/hello")//test endpoint for communication
    public ResponseEntity<String> getUserForLogin(){
    return ResponseEntity.ok("im deployed and working");
}

    @GetMapping("/login")//implemented in front
    public ResponseEntity<UserCredentialsDto> getUserForLogin(String login){
        return ResponseEntity.ok(userFacade.getUserForLogin(login));
    }

    @GetMapping("/login_validator")//implemented in front
    public ResponseEntity<Boolean> isLoginTaken(@RequestParam String login) {
        return ResponseEntity.ok(userFacade.checkIfLoginIsTaken(login));
    }

    @PostMapping("/create_user")//implemented in front
    public ResponseEntity<String> createUser(@RequestBody CreatedUserDto userInputDto) {
        return ResponseEntity.ok(userFacade.createUser(userInputDto));
    }
    ///////////////////////////////beers////////////////////////////////////////////////
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")//implemented in front
    @GetMapping("/get_beers")
    public ResponseEntity<List<BeerDto>> getBeerList() throws BeerDbIsEmptyException {//implemented in front
        return ResponseEntity.ok(userFacade.getBeerList());
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")//implemented in front
    @PostMapping("/add_beer_to_favorite")
    public ResponseEntity<String> addBeerToFavorite(@CurrentSecurityContext SecurityContext context,@RequestParam String beerName){
        return ResponseEntity.ok(userFacade.addBeerToFavorite(beerName,context.getAuthentication().getName()));
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")//implemented in front
    @DeleteMapping("/remove_beer_from_favorite")
    public ResponseEntity<String> removeBeerFromFavorite(@CurrentSecurityContext SecurityContext context,@RequestParam String beerName){
        return ResponseEntity.ok(userFacade.removeBeerFromFavorite(beerName,context.getAuthentication().getName()));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")//implemented in front
    @GetMapping("/get_my_favorite_beers")
    public ResponseEntity<List<BeerDto>> getMyFavoriteBeers(@CurrentSecurityContext SecurityContext context){
        return ResponseEntity.ok(userFacade.getBeerFavoriteList(context.getAuthentication().getName()));
    }
    ///////////////////////////////meals////////////////////////////////////////////////

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")//implemented in front
    @GetMapping("/get_meals")
    public ResponseEntity<List<MealDto>> getMealList() throws MealDbIsEmptyException {
        return ResponseEntity.ok(userFacade.getMealList());
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")//implemented in front
    @PostMapping("/add_meal_to_favorite")
    public ResponseEntity<String> addMealToFavorite(@CurrentSecurityContext SecurityContext context,@RequestParam String mealName){
        return ResponseEntity.ok(userFacade.addMealToFavorite(mealName,context.getAuthentication().getName()));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")//implemented in front
    @DeleteMapping("/remove_meal_from_favorite")
    public ResponseEntity<String> removeMealFromFavorite(@CurrentSecurityContext SecurityContext context,@RequestParam String mealName){
        return ResponseEntity.ok(userFacade.removeMealFromFavorite(mealName,context.getAuthentication().getName()));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")//implemented in front
    @GetMapping("/get_my_favorite_meals")
    public ResponseEntity<List<MealDto>> getMyFavoriteMeals(@CurrentSecurityContext SecurityContext context){
        return ResponseEntity.ok(userFacade.getMealFavoriteList(context.getAuthentication().getName()));
    }

}

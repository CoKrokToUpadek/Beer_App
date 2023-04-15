package com.cokroktoupadek.beersandmealsbackend.controller;



import com.cokroktoupadek.beersandmealsbackend.domain.dto.user.UserDto;
import com.cokroktoupadek.beersandmealsbackend.errorhandlers.BeerNotFoundException;
import com.cokroktoupadek.beersandmealsbackend.facade.AdminFacade;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {

    AdminFacade adminFacade;

    ///////////////////////////////administrative////////////////////////////////////////////////


    //created for encryption of users created using MySqlWorkBench
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PutMapping("/encode_user_password")
    public ResponseEntity<String> encodeUserPassword(@RequestParam String login){
        return ResponseEntity.ok(adminFacade.encodePassword(login));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PutMapping("/set_user_status")//implemented in front
    public ResponseEntity<String> changeUserStatusByLogin(@RequestParam String login,@RequestParam Integer status){
        return ResponseEntity.ok(adminFacade.setUserStatus(login,status ));
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PutMapping("/set_user_role")//implemented in front
    public ResponseEntity<String> setUserRoleByLogin(@RequestParam String login, @RequestParam String role){
        return ResponseEntity.ok(adminFacade.setUserRole(login,role));
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/get_user_list")//implemented in front
    public ResponseEntity<List<UserDto>> getUsersList() {
        return ResponseEntity.ok(adminFacade.getUsers());
    }
    ///////////////////////////////meals////////////////////////////////////////////////
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/update_meals_db")//implemented in front
    public ResponseEntity<String> updateLocalMealsDb() {
        return ResponseEntity.ok(adminFacade.updateMealDbFacade());
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping("/delete_meal")//implemented in front
    public ResponseEntity<String> deleteMealFromDb(@RequestParam String mealName){
        return ResponseEntity.ok(adminFacade.deleteSingleMeal(mealName));
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping("/delete_all_meals")//implemented in front
    public ResponseEntity<String> deleteAllMealsFromDb() {
        return ResponseEntity.ok(adminFacade.deleteAllMeals());
    }

    ///////////////////////////////beers////////////////////////////////////////////////
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/update_beer_db")//implemented in front
    public ResponseEntity<String> updateLocalBeersDb() {
        return ResponseEntity.ok(adminFacade.updateBeerDbFacade());
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping("/delete_beer")//implemented in front
    public ResponseEntity<String> deleteBeerFromDb(@RequestParam String beerName) throws BeerNotFoundException {
        return ResponseEntity.ok(adminFacade.deleteSingleBeer(beerName));
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping("/delete_all_beers")//implemented in front
    public ResponseEntity<String> deleteAllBeersFromDb()  {
        return ResponseEntity.ok(adminFacade.deleteAllBeers());
    }

}

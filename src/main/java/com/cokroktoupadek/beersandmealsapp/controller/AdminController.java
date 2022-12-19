package com.cokroktoupadek.beersandmealsapp.controller;



import com.cokroktoupadek.beersandmealsapp.domain.dto.user.UserDto;
import com.cokroktoupadek.beersandmealsapp.errorhandlers.BeerNotFoundException;
import com.cokroktoupadek.beersandmealsapp.facade.AdminFacade;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {

    AdminFacade adminFacade;
    ///////////////////////////////users////////////////////////////////////////////////

    //created for encryption for users created using MySqlWorkBench
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PutMapping("/encode_user_password")
    public ResponseEntity<String> encodeUserPassword(@RequestParam String login){
        return ResponseEntity.ok(adminFacade.encodePassword(login));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PutMapping("/set_user_status")
    public ResponseEntity<String> changeUserStatusByLogin(@RequestParam String login,@RequestParam Integer status){
        return ResponseEntity.ok(adminFacade.setUserStatus(login,status ));
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PutMapping("/set_user_role")
    public ResponseEntity<String> setUserRoleByLogin(@RequestParam String login, @RequestParam String role){
        return ResponseEntity.ok(adminFacade.setUserRole(login,role));
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/get_user_list")
    public ResponseEntity<List<UserDto>> getUsersList() {
        return ResponseEntity.ok(adminFacade.getUsers());
    }
    ///////////////////////////////meals////////////////////////////////////////////////
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/update_meals_db")
    public ResponseEntity<String> updateLocalMealsDb() {
        return ResponseEntity.ok(adminFacade.updateMealDbFacade());
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping("/delete_meal")
    public ResponseEntity<String> deleteMealFromDb(@RequestParam String name){
        return ResponseEntity.ok(adminFacade.deleteSingleMeal(name));
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping("/delete_all_meals")
    public ResponseEntity<String> deleteAllMealsFromDb() {
        return ResponseEntity.ok(adminFacade.deleteAllMeals());
    }

    ///////////////////////////////beers////////////////////////////////////////////////
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/update_beer_db")
    public ResponseEntity<String> updateLocalBeersDb() {
        return ResponseEntity.ok(adminFacade.updateBeerDbFacade());
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping("/delete_beer")
    public ResponseEntity<String> deleteBeerFromDb(@RequestParam String beerName) throws BeerNotFoundException {
        return ResponseEntity.ok(adminFacade.deleteSingleBeer(beerName));
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping("/delete_all_beers")
    public ResponseEntity<String> deleteAllBeersFromDb()  {
        return ResponseEntity.ok(adminFacade.deleteAllBeers());
    }



///////////////////////////////test endpoints////////////////////////////////////////////////

    @GetMapping("/dummy")
    public ResponseEntity<String> dummy(@CurrentSecurityContext SecurityContext context){
        return ResponseEntity.ok(context.getAuthentication().getName()+ " have access to open method");
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/dummy_for_authorised")
    public ResponseEntity<String> dummy2(@CurrentSecurityContext SecurityContext context){
        return ResponseEntity.ok(context.getAuthentication().getName()+ " have access to protected method");
    }
}

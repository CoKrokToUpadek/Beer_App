package com.cokroktoupadek.beersandmealsapp.controller;



import com.cokroktoupadek.beersandmealsapp.errorhandlers.BeerDbIsEmptyException;
import com.cokroktoupadek.beersandmealsapp.errorhandlers.BeerNotFoundException;
import com.cokroktoupadek.beersandmealsapp.facade.AdminFacade;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {

    AdminFacade adminFacade;
    ///////////////////////////////users////////////////////////////////////////////////
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PutMapping("/set_status")
    public ResponseEntity<String> changeUserStatusByLogin(@RequestParam String login,@RequestParam Integer status){
        return ResponseEntity.ok(adminFacade.setUserStatus(login,status ));
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @Secured("ROLE_ADMIN")
    @PutMapping("/set_user_status_to_admin")
    public ResponseEntity<String> setUserStatusByLogin(@RequestParam String login, @RequestParam String status){
        return ResponseEntity.ok(adminFacade.setUserStatus(login,status));
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
    public ResponseEntity<String> deleteAllBeersFromDb() throws BeerDbIsEmptyException {
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

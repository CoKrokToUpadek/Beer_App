package com.cokroktoupadek.beer_ap.controller;



import com.cokroktoupadek.beer_ap.errorhandlers.BeerDbIsEmptyException;
import com.cokroktoupadek.beer_ap.facade.AdminFacade;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {

    AdminFacade adminFacade;

    @Secured("ROLE_ADMIN")
    @PostMapping("/update_db")
    public ResponseEntity<String> updateLocalBeersDb() {
        return ResponseEntity.ok(adminFacade.updateDbFacade());
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/delete_beer")
    public ResponseEntity<String> deleteBeerFromDb(@RequestParam String beerName) {
        return ResponseEntity.ok(adminFacade.deleteSingleBeer(beerName));
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/delete_all_beers")
    public ResponseEntity<String> deleteAllBeersFromDb() throws BeerDbIsEmptyException {
        return ResponseEntity.ok(adminFacade.deleteAllBeers());
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("/ban_user")
    public ResponseEntity<String> blockUserById(@RequestParam Long id){
        return ResponseEntity.ok("User with requested ID was blocked");
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("/set_user_status_to_admin")
    public ResponseEntity<String> setUserStatusById(@RequestParam Long id){
        return ResponseEntity.ok("User with requested ID is now admin");
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

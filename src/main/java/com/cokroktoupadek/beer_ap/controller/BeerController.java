package com.cokroktoupadek.beer_ap.controller;


import com.cokroktoupadek.beer_ap.domain.dto.beer.BeerDto;
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
public class BeerController {

    @Secured("ROLE_ADMIN")
    @GetMapping("/update_db")
    public ResponseEntity<String> UpdateLocalBeersDb() {
        return ResponseEntity.ok("list was updated");
    }

    @Secured({"ROLE_ADMIN","ROLE_USER"})
    @GetMapping("/get_beers")
    public ResponseEntity<List<BeerDto>> getBeerList() {
        List<BeerDto> beerDtos = new ArrayList<>();
        return ResponseEntity.ok(beerDtos);
    }

}

package com.cokroktoupadek.beer_ap.controller;


import com.cokroktoupadek.beer_ap.domain.dto.beer.BeerDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/beers")
@AllArgsConstructor
public class BeerController {

    @GetMapping("/get_beers")
    public ResponseEntity<List<BeerDto>> UpdateLocalBeersDb() {
        List<BeerDto> beerDtos = new ArrayList<>();
        return ResponseEntity.ok(beerDtos);
    }

}

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
    public ResponseEntity<List<BeerDto>> getBeers(){
        List<BeerDto>beerDtos=new ArrayList<>();
        return ResponseEntity.ok(beerDtos);
    }

    @GetMapping("/get_beers/{beerId}")
    public ResponseEntity<List<BeerDto>> getBeers(@PathVariable Long beerId){
        List<BeerDto>beerDtos=new ArrayList<>();
        return ResponseEntity.ok(beerDtos);
    }
}

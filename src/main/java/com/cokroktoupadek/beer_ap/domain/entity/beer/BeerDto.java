package com.cokroktoupadek.beer_ap.domain.entity.beer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "beers")
public class BeerDto {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="beer_id")
    private Long id;

    @Column(name="beer_description")
    private String description;

    @Column(name="beer_imageUrl")
    private String imageUrl;

    @Column(name="beer_abv")
    private Double abv;

    @Column(name="beer_ibu")
    private Double ibu;

    @Column(name="beer_targetFg")
    private Integer targetFg;

    @Column(name="beer_targetOg")
    private Integer targetOg;

    @Column(name="beer_ebc")
    protected Double ebc;

    @Column(name="beer_srm")
    private Double srm;

    @Column(name="beer_ph")
    private Double ph;

    @Column(name="beer_attenuationLevel")
    private Double attenuationLevel;

    @Column(name="beer_volumeDto")
    private VolumeEntity volumeDto;

    @Column(name="beer_boilVolumeDto")
    private BoilVolumeEntity boilVolumeDto;

    @Column(name="beer_methodDto")
    private MethodEntity methodDto;

    @Column(name="beer_ingredientsDto")
    private IngredientsEntity ingredientsDto;

    @Column(name="beer_foodPairing")
    private List<String> foodPairing;

    @Column(name="beer_brewers_tips")
    private String brewers_tips;

    @Column(name="beer_contributed_by")
    private String contributed_by;
}

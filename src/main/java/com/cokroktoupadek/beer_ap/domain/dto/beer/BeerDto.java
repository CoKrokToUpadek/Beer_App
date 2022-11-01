package com.cokroktoupadek.beer_ap.domain.dto.beer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BeerDto {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("description")
    private String description;
    @JsonProperty( "image_url")
    private String imageUrl;
    @JsonProperty("abv")
    private Double abv;
    @JsonProperty("ibu")
    private Double ibu;
    @JsonProperty("target_fg")
    private Integer targetFg;
    @JsonProperty("target_og")
    private Integer targetOg;
    @JsonProperty("ebc")
    protected Double ebc;
    @JsonProperty("srm")
    private Double srm;
    @JsonProperty("ph")
    private Double ph;
    @JsonProperty("attenuation_level")
    private Double attenuationLevel;
    @JsonProperty("volume")
    private VolumeDto volumeDto;
    @JsonProperty("boil_volume")
    private BoilVolumeDto boilVolumeDto;
    @JsonProperty("method")
    private MethodDto methodDto;
    @JsonProperty("food_pairing")
    private List<String> foodPairing;
    @JsonProperty("brewers_tips")
    private String brewers_tips;
    @JsonProperty("contributed_by")
    private String contributed_by;


}

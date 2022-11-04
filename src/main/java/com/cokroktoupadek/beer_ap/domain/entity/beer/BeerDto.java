package com.cokroktoupadek.beer_ap.domain.entity.beer;

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
    @JsonProperty("ingredients")
    private IngredientsDto ingredientsDto;
    @JsonProperty("food_pairing")
    private List<String> foodPairing;
    @JsonProperty("brewers_tips")
    private String brewers_tips;
    @JsonProperty("contributed_by")
    private String contributed_by;

    @Override
    public String toString() {
        return "beer{" +
                "\"description\": " + description +
                "\n\"imageUrl\": " + imageUrl +
                "\n\"abv\": " + abv +
                "\n\"ibu\": " + ibu +
                "\n\"targetFg\": " + targetFg +
                "\n\"targetOg\": " + targetOg +
                "\n\" ebc\": " + ebc +
                "\n\"srm\": " + srm +
                "\n\"ph\": " + ph +
                "\n\"attenuationLevel\": " + attenuationLevel +
                "\n" + volumeDto +
                "\n" + boilVolumeDto +
                "\n\"" + methodDto +
                "\n\"" + ingredientsDto +
                "\n\"foodPairing\": " + foodPairing +
                "\n\"brewers_tips\": " + brewers_tips+
                "\n\"contributed_by\": " + contributed_by +
                "\n"+'}';
    }
}

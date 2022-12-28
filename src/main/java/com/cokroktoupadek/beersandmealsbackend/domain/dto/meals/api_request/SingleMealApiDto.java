package com.cokroktoupadek.beersandmealsbackend.domain.dto.meals.api_request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SingleMealApiDto {

    @JsonProperty("idMeal")
    private Long id;

    @JsonProperty("strMeal")
    private String name;

    @JsonProperty("strCategory")
    private String category;

    @JsonProperty("strArea")
    private String area;

    @JsonProperty("strInstructions")
    private String instruction;

    @JsonProperty("strMealThumb")
    private String thumbnail;

    @JsonProperty("strTags")
    private String tags;

    @JsonProperty("strYoutube")
    private String youtubeLink;

    @JsonProperty("strSource")
    private String source;

    @JsonProperty("strIngredient1")
    private String ingredient1;

    @JsonProperty("strIngredient2")
    private String ingredient2;

    @JsonProperty("strIngredient3")
    private String ingredient3;

    @JsonProperty("strIngredient4")
    private String ingredient4;

    @JsonProperty("strIngredient5")
    private String ingredient5;

    @JsonProperty("strIngredient6")
    private String ingredient6;

    @JsonProperty("strIngredient7")
    private String ingredient7;

    @JsonProperty("strIngredient8")
    private String ingredient8;

    @JsonProperty("strIngredient9")
    private String ingredient9;

    @JsonProperty("strIngredient10")
    private String ingredient10;

    @JsonProperty("strIngredient11")
    private String ingredient11;

    @JsonProperty("strIngredient12")
    private String ingredient12;

    @JsonProperty("strIngredient13")
    private String ingredient13;

    @JsonProperty("strIngredient14")
    private String ingredient14;

    @JsonProperty("strIngredient15")
    private String ingredient15;

    @JsonProperty("strIngredient16")
    private String ingredient16;

    @JsonProperty("strIngredient17")
    private String ingredient17;

    @JsonProperty("strIngredient18")
    private String ingredient18;

    @JsonProperty("strIngredient19")
    private String ingredient19;

    @JsonProperty("strIngredient20")
    private String ingredient20;

    @JsonProperty("strMeasure1")
    private String measure1;

    @JsonProperty("strMeasure2")
    private String measure2;

    @JsonProperty("strMeasure3")
    private String measure3;

    @JsonProperty("strMeasure4")
    private String measure4;

    @JsonProperty("strMeasure5")
    private String measure5;

    @JsonProperty("strMeasure6")
    private String measure6;

    @JsonProperty("strMeasure7")
    private String measure7;

    @JsonProperty("strMeasure8")
    private String measure8;

    @JsonProperty("strMeasure9")
    private String measure9;

    @JsonProperty("strMeasure10")
    private String measure10;

    @JsonProperty("strMeasure11")
    private String measure11;

    @JsonProperty("strMeasure12")
    private String measure12;

    @JsonProperty("strMeasure13")
    private String measure13;

    @JsonProperty("strMeasure14")
    private String measure14;

    @JsonProperty("strMeasure15")
    private String measure15;

    @JsonProperty("strMeasure16")
    private String measure16;

    @JsonProperty("strMeasure17")
    private String measure17;

    @JsonProperty("strMeasure18")
    private String measure18;

    @JsonProperty("strMeasure19")
    private String measure19;

    @JsonProperty("strMeasure20")
    private String measure20;


}

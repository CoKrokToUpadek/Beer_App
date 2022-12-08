package com.cokroktoupadek.beer_ap.domain.dto.meals;

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
public class MealsInputMainObjectDto {
    @JsonProperty("meals")
    private List<SingleMealDto> singleMealDtoList;
}

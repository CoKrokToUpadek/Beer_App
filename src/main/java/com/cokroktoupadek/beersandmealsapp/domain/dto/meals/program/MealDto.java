package com.cokroktoupadek.beersandmealsapp.domain.dto.meals.program;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MealDto {

    private Long id;

    private String name;

    private String category;

    private String area;

    private String instruction;

    private String thumbnail;

    private String tags;

    private String youtubeLink;

    List<IngredientAndMeasureDto> ingredientsAndMeasureDtoList;

    private String source;
}

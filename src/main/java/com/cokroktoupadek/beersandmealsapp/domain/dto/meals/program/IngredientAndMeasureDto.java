package com.cokroktoupadek.beersandmealsapp.domain.dto.meals.program;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredientAndMeasureDto {
    private String IngredientName;
    private String IngredientMeasure;
}

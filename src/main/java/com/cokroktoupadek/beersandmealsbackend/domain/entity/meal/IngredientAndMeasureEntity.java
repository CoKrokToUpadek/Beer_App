package com.cokroktoupadek.beersandmealsbackend.domain.entity.meal;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Entity(name = "ingredients_and_measures")
public class IngredientAndMeasureEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="ingredient_and_measure_id")
    private Long id;

    @NonNull
    @Column(name="ingredient_and_measure_ingredient_name")
    private String ingredientName;

    @NonNull
    @Column(name="ingredient_and_measure_ingredient_measure")
    private String ingredientMeasure;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="meal_id")
    private MealEntity mealEntity;
}

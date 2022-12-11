package com.cokroktoupadek.beersandmealsapp.domain.entity.meal;

import com.cokroktoupadek.beersandmealsapp.domain.entity.user.UserEntity;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Entity(name = "meals")
public class MealEntity {
    //id is directly taken from api, so I don't need to generate it
    @Id
    @Column(name="meal_id")
    private Long id;

    @NonNull
    @Column(name="meal_name")
    private String name;

    @NonNull
    @Column(name="meal_category")
    private String category;

    @NonNull
    @Column(name="meal_area")
    private String area;

    @NonNull
    @Column(name="meal_instruction",columnDefinition = "VARCHAR(2000)")
    private String instruction;

    @NonNull
    @Column(name="meal_thumbnail")
    private String thumbnail;

    @NonNull
    @Column(name="meal_tags")
    private String tags;

    @Column(name="meal_youtube_link")
    private String youtubeLink;

    @NonNull
    @OneToMany(mappedBy = "mealEntity",cascade = CascadeType.ALL)
    private List<IngredientAndMeasureEntity> ingredientsAndMeasureEntityList;

    @Column(name="meal_source")
    private String source;

    @ManyToMany(fetch = FetchType.EAGER,mappedBy = "favouredMeals")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<UserEntity> mealFavouredBy;
}

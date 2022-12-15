package com.cokroktoupadek.beersandmealsapp.domain.entity.meal;

import com.cokroktoupadek.beersandmealsapp.domain.entity.user.UserEntity;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

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
    @Column(name="meal_instruction",columnDefinition = "VARCHAR(5000)")
    private String instruction;

    @NonNull
    @Column(name="meal_thumbnail")
    private String thumbnail;

//    @NonNull
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MealEntity that = (MealEntity) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!name.equals(that.name)) return false;
        if (!category.equals(that.category)) return false;
        if (!area.equals(that.area)) return false;
        if (!instruction.equals(that.instruction)) return false;
        if (!thumbnail.equals(that.thumbnail)) return false;
        if (!Objects.equals(tags, that.tags)) return false;
        if (!Objects.equals(youtubeLink, that.youtubeLink)) return false;
        return Objects.equals(source, that.source);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + name.hashCode();
        result = 31 * result + category.hashCode();
        result = 31 * result + area.hashCode();
        result = 31 * result + instruction.hashCode();
        result = 31 * result + thumbnail.hashCode();
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        result = 31 * result + (youtubeLink != null ? youtubeLink.hashCode() : 0);
        result = 31 * result + (source != null ? source.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MealEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", area='" + area + '\'' +
                ", instruction='" + instruction + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", tags='" + tags + '\'' +
                ", youtubeLink='" + youtubeLink + '\'' +
                ", source='" + source + '\'' +
                '}';
    }
}

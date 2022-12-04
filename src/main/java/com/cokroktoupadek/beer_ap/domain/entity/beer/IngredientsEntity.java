package com.cokroktoupadek.beer_ap.domain.entity.beer;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Entity(name = "ingredients")
public class IngredientsEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="ingredient_id")
    private Long id;

    @NonNull
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)//unidirectional
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(
            name = "ingredient_malts",
            joinColumns = {@JoinColumn(name = "ingredient_id")},
            inverseJoinColumns = {@JoinColumn(name = "malt_id")})
    private List<MaltEntity> maltsList;

    @NonNull
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)//unidirectional
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(
            name = "ingredient_hops",
            joinColumns = {@JoinColumn(name = "ingredient_id")},
            inverseJoinColumns = {@JoinColumn(name = "hops_id")})
    private List<HopsEntity> hopsList;

    @NonNull
    @Column(name="ingredient_yeast")
    private String yeast;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IngredientsEntity that = (IngredientsEntity) o;

        if (!maltsList.equals(that.maltsList)) return false;
        if (!hopsList.equals(that.hopsList)) return false;
        return yeast.equals(that.yeast);
    }

    @Override
    public int hashCode() {
        int result = maltsList.hashCode();
        result = 31 * result + hopsList.hashCode();
        result = 31 * result + yeast.hashCode();
        return result;
    }
}

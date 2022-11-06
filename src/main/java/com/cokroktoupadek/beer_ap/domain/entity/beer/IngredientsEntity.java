package com.cokroktoupadek.beer_ap.domain.entity.beer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "ingredients")
public class IngredientsEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="ingredient_id")
    private Long id;

    @ManyToMany//bidirectional
    @JoinTable(
            name = "ingredient_malts",
            joinColumns = {@JoinColumn(name = "ingredient_id")},
            inverseJoinColumns = {@JoinColumn(name = "malt_id")})
    private List<MaltEntity> maltsList;

    @ManyToMany//bidirectional
    @JoinTable(
            name = "ingredient_hops",
            joinColumns = {@JoinColumn(name = "ingredient_id")},
            inverseJoinColumns = {@JoinColumn(name = "malt_id")})
    private List<HopsEntity> hopsList;

    @Column(name="ingredient_yeast")
    private String yeast;

    @OneToMany(mappedBy = "ingredients")
    private List<BeerEntity> beers;
}

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

    @Column(name="ingredient_malts")
    private List<MaltEntity> maltDtoList;

    @Column(name="ingredient_hops")
    private List<HopsEntity> hopsDtoList;

    @Column(name="ingredient_yeast")
    private String yeast;
}

package com.cokroktoupadek.beer_ap.domain.entity.beer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "malts")
public class MaltEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="malt_id")
    private Long id;

    @Column(name="malt_name")
    private String name;

    @ManyToOne//bidirectional
    @JoinColumn(name = "malt_amount", referencedColumnName = "amount_value_id")
    private AmountEntity amount;

    @ManyToMany(mappedBy = "maltsList")
    private List<IngredientsEntity> ingredients;
}

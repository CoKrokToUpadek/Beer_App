package com.cokroktoupadek.beer_ap.domain.entity.beer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "hops")
public class HopsEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="hop_id")
    private Long id;

    @Column(name="hop_name")
    private String name;

    @ManyToOne//bidirectional
    @JoinColumn(name = "hop_amount", referencedColumnName = "amount_value_id")
    private AmountEntity amount;

    @Column(name="hop_add")
    private String add;

    @Column(name="hop_attribute")
    private String attribute;

    @ManyToMany(mappedBy = "hopsList")
    private List<IngredientsEntity> ingredients;
}

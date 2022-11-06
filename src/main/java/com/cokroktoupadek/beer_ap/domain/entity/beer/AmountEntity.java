package com.cokroktoupadek.beer_ap.domain.entity.beer;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "amount_values")
public class AmountEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="amount_value_id")
    private Long id;

    @Column(name="amount_value_value")
    private double value;

    @Column(name="amount_value_unit")
    private String unit;

    @OneToMany(mappedBy = "amount")
    private List<MaltEntity> maltsList;

    @OneToMany(mappedBy = "amount")
    private List<HopsEntity> hopsList;
}

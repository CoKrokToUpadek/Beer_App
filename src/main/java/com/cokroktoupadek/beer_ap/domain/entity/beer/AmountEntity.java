package com.cokroktoupadek.beer_ap.domain.entity.beer;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "amounts")
public class AmountEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="amount_id")
    private Long id;

    @Column(name="amount value")
    private Double value;

    @Column(name="amount_unit")
    private String unit;
}

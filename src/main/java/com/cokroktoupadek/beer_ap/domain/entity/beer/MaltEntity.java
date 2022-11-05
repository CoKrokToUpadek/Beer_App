package com.cokroktoupadek.beer_ap.domain.entity.beer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @Column(name="malt_amount")
    private AmountEntity amountDto;
}

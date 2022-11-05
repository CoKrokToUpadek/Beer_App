package com.cokroktoupadek.beer_ap.domain.entity.beer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @Column(name="hop_amount")
    private AmountEntity amountDto;

    @Column(name="hop_add")
    private String add;

    @Column(name="hop_attribute")
    private String attribute;
}

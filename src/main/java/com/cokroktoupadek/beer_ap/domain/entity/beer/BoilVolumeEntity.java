package com.cokroktoupadek.beer_ap.domain.entity.beer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "boil_volumes")
public class BoilVolumeEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="boil_volume_id")
    private Long id;
    @Column(name="boil_volume_value")
    private Integer value;
    @Column(name="boil_volume_unit")
    private String unit;
}

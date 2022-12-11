package com.cokroktoupadek.beersandmealsapp.domain.entity.beer;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Entity(name = "boil_volumes")
public class BoilVolumeEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="boil_volume_id")
    private Long id;

    @NonNull
    @Column(name="boil_volume_value")
    private Integer value;

    @NonNull
    @Column(name="boil_volume_unit")
    private String unit;

    @OneToMany(mappedBy = "boilVolume")
    private List<BeerEntity> beerBoilVolumes;
}

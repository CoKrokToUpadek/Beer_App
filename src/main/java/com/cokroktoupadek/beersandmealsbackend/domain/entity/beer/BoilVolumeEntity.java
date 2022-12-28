package com.cokroktoupadek.beersandmealsbackend.domain.entity.beer;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BoilVolumeEntity that = (BoilVolumeEntity) o;

        if (!id.equals(that.id)) return false;
        if (!value.equals(that.value)) return false;
        return unit.equals(that.unit);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + value.hashCode();
        result = 31 * result + unit.hashCode();
        return result;
    }
}

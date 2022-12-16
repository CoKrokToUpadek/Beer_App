package com.cokroktoupadek.beersandmealsapp.domain.entity.beer;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Entity(name = "volumes")
public class VolumeEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="volume_id")
    private Long id;

    @NonNull
    @Column(name="volume_value")
    private Integer value;
    @NonNull
    @Column(name="volume_unit")
    private String unit;

    @OneToMany(mappedBy = "volume")
    private  List<BeerEntity> beerVolumes;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VolumeEntity that = (VolumeEntity) o;

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

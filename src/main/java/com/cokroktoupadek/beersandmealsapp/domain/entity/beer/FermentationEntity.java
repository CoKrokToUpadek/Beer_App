package com.cokroktoupadek.beersandmealsapp.domain.entity.beer;

import lombok.*;

import javax.persistence.*;


@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Entity(name = "fermentations")
public class FermentationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fermentation_id")
    private Long id;

    @NonNull
    @ManyToOne(fetch = FetchType.EAGER)//bidirectional
    @JoinColumn(name = "fermentation_temp", referencedColumnName = "temp_value_id")
    private TempEntity temp;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FermentationEntity that = (FermentationEntity) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}

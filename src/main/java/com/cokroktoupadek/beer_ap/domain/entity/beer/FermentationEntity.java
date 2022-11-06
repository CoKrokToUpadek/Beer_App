package com.cokroktoupadek.beer_ap.domain.entity.beer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "fermentations")
public class FermentationEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="fermentation_id")
    private Long id;

    @ManyToOne//bidirectional
    @JoinColumn(name = "fermentation_temp", referencedColumnName = "temp_value_id")
    private TempEntity temp;
}

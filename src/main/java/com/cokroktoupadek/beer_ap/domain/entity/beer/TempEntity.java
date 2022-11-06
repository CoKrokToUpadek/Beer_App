package com.cokroktoupadek.beer_ap.domain.entity.beer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "temp_values")
public class TempEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="temp_value_id")
    private Long id;

    @Column(name="temp_value_value")
    private int value;

    @Column(name="temp_value_unit")
    private String unit;

    @OneToMany(mappedBy = "temp")
    private List<MashTempEntity> mashTempList;

    @OneToMany(mappedBy = "temp")
    private List<FermentationEntity> fermentationTempList;

}

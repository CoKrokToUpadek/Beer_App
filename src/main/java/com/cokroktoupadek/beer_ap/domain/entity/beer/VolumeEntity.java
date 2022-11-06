package com.cokroktoupadek.beer_ap.domain.entity.beer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "volumes")
public class VolumeEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="volume_id")
    private Long id;

    @Column(name="temp_value")
    private Integer value;

    @Column(name="temp_unit")
    private String unit;


}

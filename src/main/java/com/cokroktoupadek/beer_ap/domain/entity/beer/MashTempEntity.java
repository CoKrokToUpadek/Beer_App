package com.cokroktoupadek.beer_ap.domain.entity.beer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "mash_temps")
public class MashTempEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="mash_temp_id")
    private Long id;

    @ManyToOne//bidirectional
    @JoinColumn(name = "mash_temp_temp", referencedColumnName = "temp_value_id")
    private TempEntity temp;

    @Column(name="mash_temp_duration")
    private Integer duration;
}

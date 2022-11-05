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
    @JsonProperty("mash_temp_temps")
    private TempEntity tempDtoList;
    @JsonProperty("mash_temp_duration")
    private Integer duration;
}

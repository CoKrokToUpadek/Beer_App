package com.cokroktoupadek.beer_ap.domain.entity.beer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MashTempDto {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("temp")
    private TempDto tempDtoList;
    @JsonProperty("duration")
    private Integer duration;
}

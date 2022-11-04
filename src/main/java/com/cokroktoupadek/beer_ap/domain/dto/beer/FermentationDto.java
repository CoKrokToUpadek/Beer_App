package com.cokroktoupadek.beer_ap.domain.dto.beer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FermentationDto {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("temp")
    private TempDto tempDto;

    @Override
    public String toString() {
        return "\"fermentation\":{"+
                "\n"+tempDto+
                "\n"+'}';
    }
}

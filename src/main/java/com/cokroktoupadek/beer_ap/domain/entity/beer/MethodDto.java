package com.cokroktoupadek.beer_ap.domain.entity.beer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MethodDto {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("mash_temp")
    private List<MashTempDto> mashTempDtoList;
    @JsonProperty("fermentation")
    private FermentationDto fermentationDto;
    @JsonProperty("twist")
    private String twist;

    @Override
    public String toString() {
        return "method{"+
                "\n\t\"mash_temp\": "+mashTempDtoList+
                "\n\t\"fermentation\": "+fermentationDto+
                "\n\t\"twist\": "+twist+
                "\n"+'}';
    }

}

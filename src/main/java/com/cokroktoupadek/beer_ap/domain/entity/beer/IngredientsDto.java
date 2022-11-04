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
public class IngredientsDto {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("malt")
    private List<MaltDto> maltDtoList;
    @JsonProperty( "amount")
    private List<HopsDto> hopsDtoList;
    @JsonProperty("yeast")
    private String yeast;
}

package com.cokroktoupadek.beer_ap.domain.dto.beer;

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
    @JsonProperty( "hops")
    private List<HopsDto> hopsDtoList;
    @JsonProperty("yeast")
    private String yeast;

    @Override
    public String toString() {
        return "ingredients{"+
                "\n\t\"malt\": "+maltDtoList+
                "\n\t\"hops\": "+hopsDtoList+
                "\n\t\"yeast\": "+yeast+
                "\n"+'}';
    }

}

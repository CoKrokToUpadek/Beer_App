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
public class TempDto {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("value")
    private Integer value;
    @JsonProperty("unit")
    private String unit;

    @Override
    public String toString() {
        return "\t\t\t\ttemp{"+
                "\n\t\t\t\t\t\"value\": "+value+
                "\n\t\t\t\t\t\"unit\": "+unit+
                "\n"+'}';
    }
}

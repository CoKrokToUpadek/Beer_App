package com.cokroktoupadek.beer_ap.domain.entity.beer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "methods")
public class MethodEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="method_id")
    private Long id;

    @Column(name="method_mash_temps")
    private List<MashTempEntity> mashTempDtoList;

    @Column(name="method_fermentation")
    private FermentationEntity fermentationDto;

    @Column(name="method_twist")
    private String twist;

}

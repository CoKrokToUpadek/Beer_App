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

    @ManyToMany(cascade = CascadeType.ALL)//unidirectional
    @JoinTable(
            name = "method_mash_temps",
            joinColumns = {@JoinColumn(name = "mash_temp_id")},
                    inverseJoinColumns = {@JoinColumn(name = "method_id")})
    private List<MashTempEntity> mashTempsList;

    @ManyToOne//unidirectional
    @JoinColumn(name = "method_fermentation", referencedColumnName = "fermentation_id")
    private FermentationEntity fermentation;

    @Column(name="method_twist")
    private String twist;

    @OneToMany(mappedBy = "method")
    private List<BeerEntity> methods;

}

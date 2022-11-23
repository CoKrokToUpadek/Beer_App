package com.cokroktoupadek.beer_ap.domain.entity.beer;

import com.cokroktoupadek.beer_ap.domain.entity.user.UserEntity;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Entity(name = "beers")
public class BeerEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="beer_id")
    private Long id;

    @NonNull
    @Column(name="beer_description",columnDefinition = "VARCHAR(1000)")
    private String description;

    @NonNull
    @Column(name="beer_image_url")
    private String imageUrl;

    @NonNull
    @Column(name="beer_abv")
    private Double abv;

    @NonNull
    @Column(name="beer_ibu")
    private Double ibu;

    @NonNull
    @Column(name="beer_target_fg")
    private Integer targetFg;

    @NonNull
    @Column(name="beer_target_og")
    private Integer targetOg;

    @NonNull
    @Column(name="beer_ebc")
    protected Double ebc;

    @NonNull
    @Column(name="beer_srm")
    private Double srm;

    @NonNull
    @Column(name="beer_ph")
    private  Double ph;

    @NonNull
    @Column(name="beer_attenuation_level")
    private Double attenuationLevel;

    @NonNull
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)//unidirectional
    @JoinColumn(name = "beer_volume", referencedColumnName = "volume_id")
    private VolumeEntity volume;

    @NonNull
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)//unidirectional
    @JoinColumn(name = "beer_boil_Volume", referencedColumnName = "boil_volume_id")
    private BoilVolumeEntity boilVolume;

    @NonNull
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)//bidirectional
    @JoinColumn(name = "beer_method", referencedColumnName = "method_id")
    private MethodEntity method;

    @NonNull
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)//bidirectional
    @JoinColumn(name = "beer_ingredients", referencedColumnName = "ingredient_id")
    private IngredientsEntity ingredients;


    @NonNull
    @ElementCollection
    @CollectionTable(name="beer_food_pairing", joinColumns=@JoinColumn(name="beer_id"))
    private List<String> foodPairing;

    @NonNull
    @Column(name="beer_brewers_tips")
    private String brewers_tips;

    @NonNull
    @Column(name="beer_contributed_by")
    private String contributed_by;

    @ManyToMany(fetch = FetchType.EAGER,mappedBy = "favouredBeers")
    @Fetch(value = FetchMode.SUBSELECT)//added for tests
    private List<UserEntity> beerFavouredBy;
}

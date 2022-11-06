package com.cokroktoupadek.beer_ap.domain.entity.beer;

import com.cokroktoupadek.beer_ap.domain.entity.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "beers")
public class BeerEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="beer_id")
    private Long id;

    @Column(name="beer_description")
    private String description;

    @Column(name="beer_image_url")
    private String imageUrl;

    @Column(name="beer_abv")
    private Double abv;

    @Column(name="beer_ibu")
    private Double ibu;

    @Column(name="beer_target_fg")
    private Integer targetFg;

    @Column(name="beer_target_og")
    private Integer targetOg;

    @Column(name="beer_ebc")
    protected Double ebc;

    @Column(name="beer_srm")
    private Double srm;

    @Column(name="beer_ph")
    private Double ph;

    @Column(name="beer_attenuation_level")
    private Double attenuationLevel;

    @ManyToOne//unidirectional
    @JoinColumn(name = "beer_volume", referencedColumnName = "volume_id")
    private VolumeEntity volume;

    @ManyToOne//unidirectional
    @JoinColumn(name = "beer_boil_Volume", referencedColumnName = "boil_volume_id")
    private BoilVolumeEntity boilVolume;

    @ManyToOne//bidirectional
    @JoinColumn(name = "beer_method", referencedColumnName = "method_id")
    private MethodEntity method;

    @ManyToOne//bidirectional
    @JoinColumn(name = "beer_ingredients", referencedColumnName = "ingredient_id")
    private IngredientsEntity ingredients;


    @ElementCollection
    @CollectionTable(name="beer_food_pairing", joinColumns=@JoinColumn(name="beer_id"))
    private List<String> foodPairing;

    @Column(name="beer_brewers_tips")
    private String brewers_tips;

    @Column(name="beer_contributed_by")
    private String contributed_by;

    @ManyToMany(mappedBy = "favouredBeers")
    private List<UserEntity> beerFavouredBy;




}

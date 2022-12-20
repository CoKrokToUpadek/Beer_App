package com.cokroktoupadek.beersandmealsapp.domain.entity.beer;


import com.cokroktoupadek.beersandmealsapp.domain.entity.user.UserEntity;
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
    @Column(name="beer_name")
    private String name;

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
    @ManyToOne(fetch = FetchType.EAGER)//bidirectional
    @JoinColumn(name = "beer_volume", referencedColumnName = "volume_id")
    private VolumeEntity volume;


    @NonNull
    @ManyToOne(fetch = FetchType.EAGER)//bidirectional
    @JoinColumn(name = "beer_boil_Volume", referencedColumnName = "boil_volume_id")
    private BoilVolumeEntity boilVolume;

    @NonNull
    @OneToOne(cascade =CascadeType.ALL)//unidirectional
    @JoinColumn(name = "beer_method", referencedColumnName = "method_id")
    private MethodEntity method;

    @NonNull
    @OneToOne(cascade =CascadeType.ALL)//unidirectional
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
    @Fetch(value = FetchMode.SUBSELECT)
    private List<UserEntity> beerFavouredBy;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BeerEntity that = (BeerEntity) o;

        if (!id.equals(that.id)) return false;
        if (!name.equals(that.name)) return false;
        if (!description.equals(that.description)) return false;
        if (!imageUrl.equals(that.imageUrl)) return false;
        if (!abv.equals(that.abv)) return false;
        if (!ibu.equals(that.ibu)) return false;
        if (!targetFg.equals(that.targetFg)) return false;
        if (!targetOg.equals(that.targetOg)) return false;
        if (!ebc.equals(that.ebc)) return false;
        if (!srm.equals(that.srm)) return false;
        if (!ph.equals(that.ph)) return false;
        if (!attenuationLevel.equals(that.attenuationLevel)) return false;
        if (!brewers_tips.equals(that.brewers_tips)) return false;
        return contributed_by.equals(that.contributed_by);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + imageUrl.hashCode();
        result = 31 * result + abv.hashCode();
        result = 31 * result + ibu.hashCode();
        result = 31 * result + targetFg.hashCode();
        result = 31 * result + targetOg.hashCode();
        result = 31 * result + ebc.hashCode();
        result = 31 * result + srm.hashCode();
        result = 31 * result + ph.hashCode();
        result = 31 * result + attenuationLevel.hashCode();
        result = 31 * result + brewers_tips.hashCode();
        result = 31 * result + contributed_by.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "BeerEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", abv=" + abv +
                ", ibu=" + ibu +
                ", targetFg=" + targetFg +
                ", targetOg=" + targetOg +
                ", ebc=" + ebc +
                ", srm=" + srm +
                ", ph=" + ph +
                ", attenuationLevel=" + attenuationLevel +
                '}';
    }
}

package com.cokroktoupadek.beer_ap.domain.entity.beer;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Entity(name = "methods")
public class MethodEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="method_id")
    private Long id;
    @NonNull
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)//unidirectional
    @Fetch(value = FetchMode.SUBSELECT)//added for tests
    @JoinTable(
            name = "method_mash_temps",
            joinColumns = {@JoinColumn(name = "mash_temp_id")},
                    inverseJoinColumns = {@JoinColumn(name = "method_id")})
    private List<MashTempEntity> mashTempsList;

    @NonNull
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)//unidirectional
    @JoinColumn(name = "method_fermentation", referencedColumnName = "fermentation_id")
    private FermentationEntity fermentation;

    @Column(name="method_twist")
    private String twist;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "method")
    private List<BeerEntity> methods;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MethodEntity that = (MethodEntity) o;

        if (!Objects.equals(mashTempsList, that.mashTempsList))
            return false;
        return Objects.equals(fermentation, that.fermentation);
    }

    @Override
    public int hashCode() {
        int result = mashTempsList.hashCode();
        result = 31 * result + fermentation.hashCode();
        return result;
    }
}

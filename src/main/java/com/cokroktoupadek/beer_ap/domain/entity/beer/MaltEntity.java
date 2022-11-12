package com.cokroktoupadek.beer_ap.domain.entity.beer;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Entity(name = "malts")
public class MaltEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="malt_id")
    private Long id;

    @NonNull
    @Column(name="malt_name")
    private String name;

    @NonNull
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)//bidirectional
    @JoinColumn(name = "malt_amount", referencedColumnName = "amount_value_id")
    private AmountEntity amount;

    @ManyToMany(mappedBy = "maltsList")
    private List<IngredientsEntity> ingredients;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MaltEntity that = (MaltEntity) o;

        if (!name.equals(that.name)) return false;
        return amount.equals(that.amount);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + amount.hashCode();
        return result;
    }
}

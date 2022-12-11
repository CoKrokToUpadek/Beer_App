package com.cokroktoupadek.beersandmealsapp.domain.entity.beer;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Entity(name = "amount_values")
public class AmountEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="amount_value_id")
    private Long id;

    @NonNull
    @Column(name="amount_value_value")
    private Double value;
    @NonNull
    @Column(name="amount_value_unit")
    private String unit;

    @OneToMany(mappedBy = "amount")
    private List<MaltEntity> maltsList;

    @OneToMany(mappedBy = "amount")
    private List<HopsEntity> hopsList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AmountEntity that = (AmountEntity) o;

        if (!value.equals(that.value)) return false;
        return unit.equals(that.unit);
    }

    @Override
    public int hashCode() {
        int result = value.hashCode();
        result = 31 * result + unit.hashCode();
        return result;
    }
}

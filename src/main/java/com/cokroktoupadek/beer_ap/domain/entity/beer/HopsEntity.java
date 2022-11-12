package com.cokroktoupadek.beer_ap.domain.entity.beer;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Entity(name = "hops")
public class HopsEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="hop_id")
    private Long id;

    @NonNull
    @Column(name="hop_name")
    private String name;

    @NonNull
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)//bidirectional
    @JoinColumn(name = "hop_amount", referencedColumnName = "amount_value_id")
    private AmountEntity amount;

    @NonNull
    @Column(name="hop_add")
    private String add;

    @NonNull
    @Column(name="hop_attribute")
    private String attribute;

    @ManyToMany(mappedBy = "hopsList")
    private List<IngredientsEntity> ingredients;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HopsEntity that = (HopsEntity) o;

        if (!name.equals(that.name)) return false;
        if (!amount.equals(that.amount)) return false;
        if (!add.equals(that.add)) return false;
        return attribute.equals(that.attribute);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + amount.hashCode();
        result = 31 * result + add.hashCode();
        result = 31 * result + attribute.hashCode();
        return result;
    }
}

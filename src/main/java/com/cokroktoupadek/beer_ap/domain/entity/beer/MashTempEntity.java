package com.cokroktoupadek.beer_ap.domain.entity.beer;


import lombok.*;

import javax.persistence.*;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Entity(name = "mash_temps")
public class MashTempEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="mash_temp_id")
    private Long id;

    @NonNull
    @ManyToOne(fetch = FetchType.EAGER)//bidirectional
    @JoinColumn(name = "mash_temp_temp", referencedColumnName = "temp_value_id")
    private TempEntity temp;

    @NonNull
    @Column(name="mash_temp_duration")
    private Integer duration;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MashTempEntity that = (MashTempEntity) o;

        if (!temp.equals(that.temp)) return false;
        return duration.equals(that.duration);
    }

    @Override
    public int hashCode() {
        int result = temp.hashCode();
        result = 31 * result + duration.hashCode();
        return result;
    }
}

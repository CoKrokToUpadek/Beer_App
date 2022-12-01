package com.cokroktoupadek.beer_ap.domain.entity.beer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;


@NamedNativeQuery(
        name = "MashTempEntity.getMashTempDuplicates",
        query = "SELECT * FROM beer_app.mash_temps inner join beer_app.temp_values on mash_temp_temp=temp_value_id where (temp_value_unit=:TEMPUNIT" +
                " and temp_value_value=:TEMPVALUE and mash_temp_duration=:MASHTEMPDURATION)",
        resultClass = MashTempEntity.class
)

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
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)//bidirectional
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

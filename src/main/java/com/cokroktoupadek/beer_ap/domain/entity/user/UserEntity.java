package com.cokroktoupadek.beer_ap.domain.entity.user;

import com.cokroktoupadek.beer_ap.domain.entity.beer.BeerEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Entity(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;

    @NonNull
    @Column(name="user_first_name")
    private String firstName;

    @NonNull
    @Column(name="user_last_name")
    private String lastName;

    @NonNull
    @Column(name="user_address")
    private String address;

    @NonNull
    @Column(name="user_email")
    private String email;

    @NonNull
    @Column(name="user_login")
    private String login;

    @NonNull
    @Column(name="user_password")
    private String password;

    @NonNull
    @Column(name="user_account_creation_date")
    private LocalDate creationDate;

    @Column(name="user_current_key")
    private Long key;

    @Column(name="user_status")
    private int status;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)//added for tests//bidirectional
    @JoinTable(
            name = "user_favorite_beers",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "beer_id")})
     private List<BeerEntity> favouredBeers;

}

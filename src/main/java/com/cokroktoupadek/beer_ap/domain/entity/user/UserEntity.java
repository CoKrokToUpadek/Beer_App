package com.cokroktoupadek.beer_ap.domain.entity.user;

import com.cokroktoupadek.beer_ap.domain.entity.beer.BeerEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;

    @Column(name="user_first_name")
    private String firstName;

    @Column(name="user_last_name")
    private String lastName;

    @Column(name="user_address")
    private String address;

    @Column(name="user_email")
    private String email;

    @Column(name="user_login")
    private String login;

    @Column(name="user_password")
    private String password;

    @Column(name="user_account_creation_date")
    private LocalDate creationDate;

    @Column(name="user_current_key")
    private Long key;

    @Column(name="user_status")
    private int status;

    @ManyToMany//bidirectional
    @JoinTable(
            name = "user_favorite_beers",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "beer_id")})
     private List<BeerEntity> favouredBeers;

}

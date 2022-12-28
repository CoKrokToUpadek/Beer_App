package com.cokroktoupadek.beersandmealsbackend.domain.entity.user;

import com.cokroktoupadek.beersandmealsbackend.domain.entity.beer.BeerEntity;
import com.cokroktoupadek.beersandmealsbackend.domain.entity.meal.MealEntity;
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
    @Column(unique=true,name="user_login")
    private String login;

    @NonNull
    @Column(name="user_password")
    private String password;

    @NonNull
    @Column(name="user_role")
    private String userRole;

    @NonNull
    @Column(name="user_account_creation_date")
    private LocalDate creationDate;
    @Column(name="user_status")
    private int status;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)//bidirectional
    @JoinTable(
            name = "user_favorite_beers",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "beer_id")})
     private List<BeerEntity> favouredBeers;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)//bidirectional
    @JoinTable(
            name = "user_favorite_meals",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "meal_id")})
    private List<MealEntity> favouredMeals;


    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", userRole='" + userRole + '\'' +
                ", creationDate=" + creationDate +
                ", status=" + status +
                ", favouredBeers=" + favouredBeers +
                ", favouredMeals=" + favouredMeals +
                '}';
    }
}

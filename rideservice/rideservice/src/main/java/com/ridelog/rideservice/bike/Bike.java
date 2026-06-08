package com.ridelog.rideservice.bike;

import com.ridelog.rideservice.ride.Ride;
import com.ridelog.rideservice.auth.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(
        name = "bikes"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "registration_number", nullable = false)
    private String registrationNumber;

    @Column(name = "chassis_number")
    private String chassisNumber;

    private String brand;

    private String model;

    private Integer year;

    private LocalDate purchaseDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(
            mappedBy = "bike",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Ride> rides = new ArrayList<>();
}
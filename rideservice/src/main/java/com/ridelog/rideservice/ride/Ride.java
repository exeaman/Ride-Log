package com.ridelog.rideservice.ride;

import com.ridelog.rideservice.bike.Bike;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "rides")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String title;

    private LocalDate rideDate;

    private Long startOdometer;

    private Long endOdometer;

    @Column(length = 1000)
    private String notes;

    private Long distance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bike_id")
    private Bike bike;
}
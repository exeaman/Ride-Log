package com.ridelog.rideservice.bike;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BikeResponse {

    private Long id;

    private String registrationNumber;
    private boolean active;
    private String chassisNumber;

    private String brand;

    private String model;

    private Integer year;

    private LocalDate purchaseDate;
}
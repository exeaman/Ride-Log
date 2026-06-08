package com.ridelog.rideservice.ride;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RideResponse {

    private Long id;

    private Long bikeId;

    private String title;

    private LocalDate rideDate;

    private Long startOdometer;

    private Long endOdometer;

    private Long distance;

    private String notes;
}
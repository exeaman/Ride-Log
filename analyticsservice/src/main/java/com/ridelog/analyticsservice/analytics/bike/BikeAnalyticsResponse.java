package com.ridelog.analyticsservice.analytics.bike;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BikeAnalyticsResponse {

    private Long bikeId;

    private String bikeName;

    private Long totalRides;

    private Long totalDistance;

    private Double averageRideDistance;

    private Long longestRide;

    private Long shortestRide;

    private LocalDate firstRideDate;

    private LocalDate latestRideDate;
}

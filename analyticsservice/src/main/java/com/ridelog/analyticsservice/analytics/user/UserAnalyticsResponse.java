package com.ridelog.analyticsservice.analytics.user;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAnalyticsResponse {

    private Long userId;

    private Long totalBikes;

    private Long totalRides;

    private Long totalDistance;

    private Double averageRideDistance;

    private Long longestRide;

    private LocalDate firstRideDate;

    private LocalDate latestRideDate;
}
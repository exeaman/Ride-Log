package com.ridelog.analyticsservice.client.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RideResponse {
    private Long id;
    private Long bikeId;
    private LocalDate rideDate;
    private Long distance;
}

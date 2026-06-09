package com.ridelog.rideservice.ride;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateRideRequest {

    @NotNull(message = "Bike id is required")
    private Long bikeId;

    @NotBlank(message = "Ride title is required")
    @Size(max = 100)
    private String title;

    @PastOrPresent(message = "Ride date cannot be in the future")
    @NotNull(message = "Ride date is required")
    private LocalDate rideDate;

    @NotNull(message = "Start odometer is required")
    @Min(0)
    private Long startOdometer;

    @NotNull(message = "End odometer is required")
    @Min(0)
    private Long endOdometer;

    @Size(max = 1000)
    private String notes;
}
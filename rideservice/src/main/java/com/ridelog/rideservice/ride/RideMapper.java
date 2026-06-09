package com.ridelog.rideservice.ride;

import org.springframework.stereotype.Component;

@Component
public class RideMapper {

    public RideResponse toResponse(Ride ride) {

        return RideResponse.builder()
                .id(ride.getId())
                .bikeId(ride.getBike().getId())
                .title(ride.getTitle())
                .rideDate(ride.getRideDate())
                .startOdometer(ride.getStartOdometer())
                .endOdometer(ride.getEndOdometer())
                .distance(ride.getDistance())
                .notes(ride.getNotes())
                .build();
    }

    public Ride toEntity(CreateRideRequest request) {

        return Ride.builder()
                .title(request.getTitle())
                .rideDate(request.getRideDate())
                .startOdometer(request.getStartOdometer())
                .endOdometer(request.getEndOdometer())
                .notes(request.getNotes())
                .build();
    }
}
package com.ridelog.rideservice.ride;

import java.util.List;

public interface RideService {


    RideResponse createRide(
            CreateRideRequest request
    );

    RideResponse getRideById(
            Long rideId
    );

    List<RideResponse> getAllRidesByBike(
            Long bikeId
    );

    RideResponse updateRide(
            Long rideId,
            UpdateRideRequest request
    );

    void deleteRide(
            Long rideId
    );
    List<RideResponse> getAllRidesByBikeInternal(
            Long bikeId
    );
}
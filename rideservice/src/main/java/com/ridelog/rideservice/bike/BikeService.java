package com.ridelog.rideservice.bike;

import java.util.List;

public interface BikeService {
    Bike findBikeEntityById(Long bikeId);
    BikeResponse createBike(Long userId, CreateBikeRequest request);

    BikeResponse getBikeById(Long bikeId);

    List<BikeResponse> getAllBikesByUser(Long userId);
    List<BikeResponse> getMyBikes();
    BikeResponse updateBike(Long bikeId, UpdateBikeRequest request);

    void deleteBike(Long bikeId);
    BikeResponse createBike(
            CreateBikeRequest request
    );
}
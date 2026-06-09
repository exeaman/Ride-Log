package com.ridelog.analyticsservice.analytics;

import com.ridelog.analyticsservice.analytics.bike.BikeAnalyticsResponse;
import com.ridelog.analyticsservice.analytics.user.UserAnalyticsResponse;
import com.ridelog.analyticsservice.client.RideServiceClient;
import com.ridelog.analyticsservice.client.dto.BikeResponse;
import com.ridelog.analyticsservice.client.dto.RideResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnalyticsServiceImpl implements AnalyticsService {

    private final RideServiceClient rideServiceClient;

    @Override
    public BikeAnalyticsResponse getBikeAnalytics(Long bikeId) {

        BikeResponse bike = rideServiceClient.getBikeById(bikeId).getData();

        List<RideResponse> rides = rideServiceClient.getRidesByBike(bikeId).getData();

        if (rides.isEmpty()) {

            return BikeAnalyticsResponse.builder().bikeId(bikeId).bikeName(bike.getBrand() + " " + bike.getModel()).totalRides(0L).totalDistance(0L).averageRideDistance(0.0).longestRide(0L).shortestRide(0L).build();
        }

        Long totalDistance = rides.stream().mapToLong(RideResponse::getDistance).sum();

        Long totalRides = (long) rides.size();

        Double averageRideDistance = rides.stream().mapToLong(RideResponse::getDistance).average().orElse(0.0);

        Long longestRide = rides.stream().mapToLong(RideResponse::getDistance).max().orElse(0);

        Long shortestRide = rides.stream().mapToLong(RideResponse::getDistance).min().orElse(0);

        LocalDate firstRideDate = rides.stream().map(RideResponse::getRideDate).min(LocalDate::compareTo).orElse(null);

        LocalDate latestRideDate = rides.stream().map(RideResponse::getRideDate).max(LocalDate::compareTo).orElse(null);

        return BikeAnalyticsResponse.builder().bikeId(bikeId).bikeName(bike.getBrand() + " " + bike.getModel()).totalRides(totalRides).totalDistance(totalDistance).averageRideDistance(averageRideDistance).longestRide(longestRide).shortestRide(shortestRide).firstRideDate(firstRideDate).latestRideDate(latestRideDate).build();
    }

    @Override
    public UserAnalyticsResponse getUserAnalytics(Long userId) {

        List<BikeResponse> bikes = rideServiceClient.getBikesByUser(userId).getData();

        List<RideResponse> allRides = bikes.stream().flatMap(bike -> rideServiceClient.getRidesByBike(bike.getId()).getData().stream()).toList();

        if (allRides.isEmpty()) {

            return UserAnalyticsResponse.builder().userId(userId).totalBikes((long) bikes.size()).totalRides(0L).totalDistance(0L).averageRideDistance(0.0).longestRide(0L).build();
        }

        Long totalDistance = allRides.stream().mapToLong(RideResponse::getDistance).sum();

        Long totalRides = (long) allRides.size();

        Double averageRideDistance = allRides.stream().mapToLong(RideResponse::getDistance).average().orElse(0.0);

        Long longestRide = allRides.stream().mapToLong(RideResponse::getDistance).max().orElse(0);

        LocalDate firstRideDate = allRides.stream().map(RideResponse::getRideDate).min(LocalDate::compareTo).orElse(null);

        LocalDate latestRideDate = allRides.stream().map(RideResponse::getRideDate).max(LocalDate::compareTo).orElse(null);

        return UserAnalyticsResponse.builder().userId(userId).totalBikes((long) bikes.size()).totalRides(totalRides).totalDistance(totalDistance).averageRideDistance(averageRideDistance).longestRide(longestRide).firstRideDate(firstRideDate).latestRideDate(latestRideDate).build();
    }
}
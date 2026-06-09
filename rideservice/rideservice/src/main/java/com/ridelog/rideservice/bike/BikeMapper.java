package com.ridelog.rideservice.bike;

import org.springframework.stereotype.Component;

@Component
public class BikeMapper {

    public BikeResponse toResponse(Bike bike) {

        return BikeResponse.builder()
                .id(bike.getId())
                .registrationNumber(bike.getRegistrationNumber())
                .chassisNumber(bike.getChassisNumber())
                .brand(bike.getBrand())
                .active(bike.isActive())
                .model(bike.getModel())
                .year(bike.getYear())
                .purchaseDate(bike.getPurchaseDate())
                .build();
    }
    public Bike toEntity(CreateBikeRequest request) {

        return Bike.builder()
                .registrationNumber(
                        request.getRegistrationNumber()
                )
                .chassisNumber(
                        request.getChassisNumber()
                )
                .brand(
                        request.getBrand()
                )
                .model(
                        request.getModel()
                )
                .year(
                        request.getYear()
                )
                .purchaseDate(
                        request.getPurchaseDate()
                )
                .build();
    }
}
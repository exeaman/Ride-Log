package com.ridelog.rideservice.bike;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BikeRepository extends JpaRepository<Bike, Long> {
    boolean existsByUserIdAndRegistrationNumber(
            Long userId,
            String registrationNumber
    );

    List<Bike> findByUserId(Long userId);
}

package com.ridelog.rideservice.bike;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BikeRepository extends JpaRepository<Bike, Long> {
    boolean existsByUserIdAndRegistrationNumber(
            Long userId,
            String registrationNumber
    );

    List<Bike> findByUserIdAndActiveTrue(Long userId);
    Optional<Bike> findByIdAndActiveTrue(Long id);
}

package com.ridelog.rideservice.ride;

import com.ridelog.rideservice.bike.Bike;
import com.ridelog.rideservice.bike.BikeService;
import com.ridelog.rideservice.exception.InvalidRideException;
import com.ridelog.rideservice.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RideServiceImpl implements RideService {

    private final RideRepository rideRepository;
    private final RideMapper rideMapper;
    private final BikeService bikeService;

    @Override
    public RideResponse createRide(CreateRideRequest request) {

        Bike bike = bikeService.findBikeEntityById(request.getBikeId());

        validateRide(bike, request.getRideDate(), request.getStartOdometer(), request.getEndOdometer());

        Ride ride = rideMapper.toEntity(request);

        ride.setBike(bike);

        ride.setDistance(request.getEndOdometer() - request.getStartOdometer());

        Ride savedRide = rideRepository.save(ride);

        return rideMapper.toResponse(savedRide);
    }

    @Override
    public RideResponse getRideById(Long rideId) {

        return rideMapper.toResponse(getRideOrThrow(rideId));
    }

    @Override
    public List<RideResponse> getAllRidesByBike(Long bikeId) {

        bikeService.findBikeEntityById(bikeId);

        return rideRepository.findByBikeId(bikeId).stream().map(rideMapper::toResponse).toList();
    }

    @Override
    public RideResponse updateRide(Long rideId, UpdateRideRequest request) {

        Ride ride = getRideOrThrow(rideId);

        validateRide(ride.getBike(), request.getRideDate(), request.getStartOdometer(), request.getEndOdometer());

        ride.setTitle(request.getTitle());

        ride.setRideDate(request.getRideDate());

        ride.setStartOdometer(request.getStartOdometer());

        ride.setEndOdometer(request.getEndOdometer());

        ride.setDistance(request.getEndOdometer() - request.getStartOdometer());

        ride.setNotes(request.getNotes());

        Ride updatedRide = rideRepository.save(ride);

        return rideMapper.toResponse(updatedRide);
    }

    @Override
    public void deleteRide(Long rideId) {

        Ride ride = getRideOrThrow(rideId);

        rideRepository.delete(ride);
    }

    private Ride getRideOrThrow(Long rideId) {

        return rideRepository.findById(rideId).orElseThrow(() -> new ResourceNotFoundException("Ride not found with id: " + rideId));
    }

    private void validateRide(Bike bike, LocalDate rideDate, Long startOdometer, Long endOdometer) {

        if (endOdometer <= startOdometer) {

            throw new InvalidRideException("End odometer must be greater than start odometer");
        }

        if (rideDate.isBefore(bike.getPurchaseDate())) {

            throw new InvalidRideException("Ride date cannot be before bike purchase date");
        }

        if (rideDate.isAfter(LocalDate.now())) {

            throw new InvalidRideException("Ride date cannot be in the future");
        }
    }
}
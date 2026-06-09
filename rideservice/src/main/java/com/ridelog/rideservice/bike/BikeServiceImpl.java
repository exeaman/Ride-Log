package com.ridelog.rideservice.bike;

import com.ridelog.rideservice.auth.user.User;
import com.ridelog.rideservice.auth.user.UserService;
import com.ridelog.rideservice.exception.DuplicateResourceException;
import com.ridelog.rideservice.exception.InvalidBikeException;
import com.ridelog.rideservice.exception.InvalidRegistrationNumberException;
import com.ridelog.rideservice.exception.ResourceNotFoundException;
import com.ridelog.rideservice.validation.RegistrationNumberValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
@RequiredArgsConstructor
public class BikeServiceImpl implements BikeService {

    private final BikeRepository bikeRepository;
    private final BikeMapper bikeMapper;
    private final UserService userService;

    @Override
    public Bike findBikeEntityById(Long bikeId) {
        return getBikeOrThrow(bikeId);
    }


    @Override
    public BikeResponse getBikeById(Long bikeId) {

        return bikeMapper.toResponse(getBikeOrThrow(bikeId));
    }

    @Override
    public List<BikeResponse> getAllBikesByUser(Long userId) {
        userService.findUserEntityById(userId);

        return bikeRepository.findByUserIdAndActiveTrue(userId).stream().map(bikeMapper::toResponse).toList();
    }

    @Override
    public BikeResponse updateBike(Long bikeId, UpdateBikeRequest request) {
        Bike bike = getBikeOrThrow(bikeId);
        validateYearAndPurchaseDate(request.getYear(), request.getPurchaseDate());
        bike.setBrand(request.getBrand());
        bike.setModel(request.getModel());
        bike.setYear(request.getYear());
        bike.setPurchaseDate(request.getPurchaseDate());

        return bikeMapper.toResponse(bikeRepository.save(bike));
    }

    @Override
    public List<BikeResponse> getMyBikes() {

        User currentUser = userService.getCurrentUser();

        List<Bike> bikes = bikeRepository.findByUserIdAndActiveTrue(currentUser.getId());

        if (bikes.isEmpty()) {

            throw new ResourceNotFoundException("No bikes found for current user");
        }

        return bikes.stream().map(bikeMapper::toResponse).toList();
    }

    @Override
    public BikeResponse createBike(Long userId, CreateBikeRequest request) {
        User user = userService.findUserEntityById(userId);

        return createBikeForUser(user, request);
    }

    @Override
    public BikeResponse createBike(CreateBikeRequest request) {
        User currentUser = userService.getCurrentUser();

        return createBikeForUser(currentUser, request);
    }

    private BikeResponse createBikeForUser(User user, CreateBikeRequest request) {

        String registrationNumber = normalizeRegistrationNumber(request.getRegistrationNumber());

        String confirmRegistrationNumber = normalizeRegistrationNumber(request.getConfirmRegistrationNumber());

        if (!registrationNumber.equals(confirmRegistrationNumber)) {

            throw new InvalidRegistrationNumberException("Registration numbers do not match");
        }

        validateRegistrationNumber(registrationNumber);

        validateYearAndPurchaseDate(request.getYear(), request.getPurchaseDate());

        if (bikeRepository.existsByUserIdAndRegistrationNumber(user.getId(), registrationNumber)) {

            throw new DuplicateResourceException("Bike with registration number " + registrationNumber + " already exists.");
        }

        Bike bike = bikeMapper.toEntity(request);

        bike.setRegistrationNumber(registrationNumber);

        bike.setUser(user);

        Bike savedBike = bikeRepository.save(bike);

        return bikeMapper.toResponse(savedBike);
    }

    @Override
    public void deleteBike(Long bikeId) {
        Bike bike = getBikeOrThrow(bikeId);
        bike.setActive(false);

        bikeRepository.save(bike);
    }

    private Bike getBikeOrThrow(Long bikeId) {

        return bikeRepository.findByIdAndActiveTrue(bikeId).orElseThrow(() -> new ResourceNotFoundException("Bike not found with id: " + bikeId));
    }

    private void validateRegistrationNumber(String registrationNumber) {

        if (!RegistrationNumberValidator.isValid(registrationNumber)) {

            throw new InvalidRegistrationNumberException("Invalid registration number");
        }
    }

    private String normalizeRegistrationNumber(String registrationNumber) {

        return registrationNumber.replaceAll("\\s+", "").toUpperCase();
    }

    private void validateYearAndPurchaseDate(Integer year, LocalDate purchaseDate) {

        if (year != null && purchaseDate != null && year > purchaseDate.getYear()) {

            throw new InvalidBikeException("Purchase date cannot be earlier than manufacturing year");
        }
    }
}


package com.ridelog.rideservice.auth.user;


public interface UserService {

    UserResponse getUserById(Long userId);

    User findUserEntityById(Long userId);

    User findUserEntityByEmail(String email);
    User getCurrentUser();
}
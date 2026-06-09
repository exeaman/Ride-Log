package com.ridelog.rideservice.auth.user;

import com.ridelog.rideservice.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    public UserResponse getUserById(Long userId) {

        return userMapper.toResponse(
                getUserOrThrow(userId)
        );
    }

    @Override
    public User findUserEntityById(Long userId) {

        return getUserOrThrow(userId);
    }

    @Override
    public User findUserEntityByEmail(String email) {

        return getUserByEmailOrThrow(email);
    }

    private User getUserByEmailOrThrow(String email) {

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found with email: " + email
                        ));
    }

    private User getUserOrThrow(Long userId) {

        return userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found with id: " + userId
                        ));
    }
}
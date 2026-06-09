package com.ridelog.rideservice.exception;

public class InvalidRegistrationNumberException
        extends RuntimeException {

    public InvalidRegistrationNumberException(
            String message
    ) {
        super(message);
    }
}
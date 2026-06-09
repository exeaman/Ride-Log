package com.ridelog.rideservice.exception;

public class InvalidBikeException
        extends RuntimeException {

    public InvalidBikeException(String message) {
        super(message);
    }
}
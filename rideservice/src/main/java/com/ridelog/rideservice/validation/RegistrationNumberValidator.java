package com.ridelog.rideservice.validation;

import java.util.regex.Pattern;

public final class RegistrationNumberValidator {

    private RegistrationNumberValidator() {
    }

    private static final Pattern TRADITIONAL_PATTERN = Pattern.compile("^[A-Z]{2}[0-9]{1,2}[A-Z]{1,3}[0-9]{4}$");

    private static final Pattern BH_PATTERN = Pattern.compile("^[0-9]{2}BH[0-9]{4}[A-Z]{2}$");

    public static boolean isValid(String registrationNumber) {

        if (registrationNumber == null || registrationNumber.isBlank()) {

            return false;
        }

        String normalized = registrationNumber.replaceAll("\\s+", "").toUpperCase();

        return TRADITIONAL_PATTERN.matcher(normalized).matches()

                ||

                BH_PATTERN.matcher(normalized).matches();
    }
}
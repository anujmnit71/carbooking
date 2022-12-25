package com.app.carbooking.common.exception;

import com.app.carbooking.domain.enumeration.BookingStatus;

import java.util.UUID;

public class InvalidBookingStateException extends CarRentalRuntimeException {

    public InvalidBookingStateException(UUID id, BookingStatus from, BookingStatus to) {
        super(String.format("Invalid booking status change for id %s from %s to %s", id, from, to));
    }

    public InvalidBookingStateException(String message) {
        super(message);
    }
}

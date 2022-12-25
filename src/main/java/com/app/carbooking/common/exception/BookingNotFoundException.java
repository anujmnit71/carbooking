package com.app.carbooking.common.exception;

import java.util.UUID;

public class BookingNotFoundException extends CarRentalRuntimeException {

    public BookingNotFoundException(UUID id) {
        super(String.format("Can't find booking with id %s", id.toString()));
    }

    public BookingNotFoundException(String message) {
        super(message);
    }
}

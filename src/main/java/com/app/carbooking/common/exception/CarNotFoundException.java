package com.app.carbooking.common.exception;

import java.util.UUID;

public class CarNotFoundException extends CarRentalRuntimeException {

    public CarNotFoundException(UUID id) {
        super(String.format("Can't find car with id %s", id.toString()));
    }

    public CarNotFoundException(String message) {
        super(message);
    }
}

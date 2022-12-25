package com.app.carbooking.common.exception;

public abstract class CarRentalRuntimeException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    protected CarRentalRuntimeException(String message) {
        super(message);
    }

    protected CarRentalRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public CarRentalRuntimeException() {
    }

}

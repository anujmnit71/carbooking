package com.app.carbooking.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Getter
public class HttpError {

    private final HttpStatus status;
    private final String message;
    private final List<String> errors;

    public HttpError(HttpStatus status, String message, List<String> errors) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public HttpError(HttpStatus status, String message, String error) {
        super();
        this.status = status;
        this.message = message;
        errors = Arrays.asList(error);
    }

    public HttpError(HttpStatus status, String message) {
        super();
        this.status = status;
        this.message = message;
        errors = Collections.emptyList();
    }
}

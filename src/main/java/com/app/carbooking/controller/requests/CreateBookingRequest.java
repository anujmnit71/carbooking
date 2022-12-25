package com.app.carbooking.controller.requests;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Getter
@Setter
@RequiredArgsConstructor
public class CreateBookingRequest {

    @NotNull
    @Future
    private ZonedDateTime startDate;

    @NotNull
    @Future
    private ZonedDateTime endDate;

    @NotNull
    private String car_id;

    @NotNull
    private String user_id;
}

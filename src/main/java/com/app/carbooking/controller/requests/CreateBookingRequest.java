package com.app.carbooking.controller.requests;

import com.app.carbooking.common.validation.ValidStartEndDate;
import lombok.*;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@ValidStartEndDate
public class CreateBookingRequest implements BookingRequestBody {

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

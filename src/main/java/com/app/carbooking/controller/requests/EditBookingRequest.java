package com.app.carbooking.controller.requests;

import com.app.carbooking.common.validation.ValidStartEndDate;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;


@Data
@AllArgsConstructor
@ValidStartEndDate
public class EditBookingRequest implements BookingRequestBody {
    @NotNull
    @Future
    private ZonedDateTime startDate;

    @NotNull
    @Future
    private ZonedDateTime endDate;
}

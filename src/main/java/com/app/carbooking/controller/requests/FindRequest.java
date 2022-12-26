package com.app.carbooking.controller.requests;

import com.app.carbooking.common.validation.ValidStartEndDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

/**
 * Slot
 */
@Data
@AllArgsConstructor
@ValidStartEndDate
public class FindRequest implements BookingRequestBody {
    public static final String DEFAULT_MODEL = "toyota";
    @NotNull
    @Future
    private ZonedDateTime startDate;

    @NotNull
    @Future
    private ZonedDateTime endDate;

    private String model;
    private int seats;
}

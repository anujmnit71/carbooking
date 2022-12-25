package com.app.carbooking.service.dto;

import com.app.carbooking.domain.enumeration.BookingStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * A DTO for the {@link com.app.carbooking.domain.Booking} entity.
 */
@Data
public class BookingDTO implements Serializable {

    @NotNull
    private UUID bookingId;

    @NotNull
    private Integer amount;

    @NotNull
    private ZonedDateTime startDate;

    @NotNull
    private ZonedDateTime endDate;

    @NotNull
    private BookingStatus status;

    @NotNull
    private String userId;

    @NotNull
    private String carId;

}

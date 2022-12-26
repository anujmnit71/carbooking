package com.app.carbooking.service.dto;

import com.app.carbooking.domain.enumeration.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * A DTO for the {@link com.app.carbooking.domain.Booking} entity.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
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

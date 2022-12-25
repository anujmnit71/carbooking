package com.app.carbooking.service.dto;

import com.app.carbooking.domain.enumeration.BookingStatus;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * A DTO for the {@link com.app.carbooking.domain.Booking} entity.
 */
@Data
public class BookingDTO implements Serializable {

    @NotNull
    private String bookingId;

    @NotNull
    private Integer amount;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    @NotNull
    private BookingStatus status;

    private UserDTO userId;

    private CarDTO carId;

}

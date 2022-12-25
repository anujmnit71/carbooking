package com.app.carbooking.service;

import com.app.carbooking.controller.requests.CreateBookingRequest;
import com.app.carbooking.controller.requests.EditBookingRequest;
import com.app.carbooking.service.dto.BookingDTO;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.Future;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface IBookingService {
    BookingDTO createBooking(CreateBookingRequest bookingRequest);

    BookingDTO findBookingById(UUID id);

    BookingDTO updateBooking(UUID id, EditBookingRequest editBookingRequest);

    BookingDTO cancelBooking(UUID id);

    BookingDTO pickUpBooking(UUID id);

    BookingDTO returnBooking(UUID id);

    List<BookingDTO> findAll();

    Set<ZonedDateTime> findAvailableDates(@Future ZonedDateTime startDate, @Future ZonedDateTime endDate);
}

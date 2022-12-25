package com.app.carbooking.service;

import com.app.carbooking.service.dto.BookingDTO;

import javax.validation.constraints.Future;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface IBookingService {
    BookingDTO createBooking(BookingDTO bookingRequest);

    BookingDTO findBookingById(UUID id);

    BookingDTO updateBooking(UUID id, BookingDTO bookingRequest);

    BookingDTO cancelBooking(UUID id);

    BookingDTO pickUpBooking(UUID id);

    BookingDTO returnBooking(UUID id);

    List<BookingDTO> findAll();

    Set<LocalDate> findAvailableDates(@Future LocalDate startDate, @Future LocalDate endDate);
}

package com.app.carbooking.util;

import com.app.carbooking.controller.requests.CreateBookingRequest;
import com.app.carbooking.domain.Booking;
import com.app.carbooking.domain.enumeration.BookingStatus;
import com.app.carbooking.service.dto.BookingDTO;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

public class EntityMocksHelper {
    public static final int amount = 10;
    public static final ZonedDateTime st = ZonedDateTime.now().plusDays(1).truncatedTo(ChronoUnit.HOURS);
    public static final ZonedDateTime end = st.plusDays(2).truncatedTo(ChronoUnit.HOURS);
    public static final BookingStatus booked = BookingStatus.BOOKED;
    public static final String user_id = "u1";
    public static final String car_id = "c1";

    public static Booking createBooking() {
        ZonedDateTime st = ZonedDateTime.now();
        BookingStatus booked = BookingStatus.BOOKED;
        Booking booking = new Booking(UUID.randomUUID(), 10, st, end, booked, user_id, car_id);
        return booking;
    }

    public static CreateBookingRequest createBookingRequest(String car_id) {
        CreateBookingRequest request = new CreateBookingRequest(st, end, car_id, user_id);
        return request;
    }

    public static BookingDTO createBookingDto() {
        ZonedDateTime st = ZonedDateTime.now();
        BookingStatus booked = BookingStatus.BOOKED;
        BookingDTO bookingDto = new BookingDTO(UUID.randomUUID(), 10, st, end, booked, user_id, car_id);
        return bookingDto;
    }
}

package com.app.carbooking.repository;

import com.app.carbooking.domain.Booking;
import com.app.carbooking.domain.enumeration.BookingStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class BookingRepositoryTest {
    @Autowired
    private BookingRepository bookingRepository;

    @Test
    public void shouldReturnsBookingWithId() {
        Booking booking = bookingRepository.save(new Booking(UUID.randomUUID(), 10,
                ZonedDateTime.now().plusDays(1), ZonedDateTime.now().plusDays(3), BookingStatus.BOOKED, "u1", "c1"));

        Optional<Booking> byId = bookingRepository.findById(booking.getBookingId());
        assertThat(byId.get().getBookingId()).isEqualTo(booking.getBookingId());
    }

    @Test
    public void shouldReturnsAllBookings() {
        bookingRepository.save(new Booking(UUID.randomUUID(), 10,
                ZonedDateTime.now().plusDays(1), ZonedDateTime.now().plusDays(3), BookingStatus.BOOKED, "u1", "c1"));

        bookingRepository.save(new Booking(UUID.randomUUID(), 10,
                ZonedDateTime.now().plusDays(5), ZonedDateTime.now().plusDays(7), BookingStatus.BOOKED, "u1", "c1"));

        List<Booking> bookingList = bookingRepository.findAll();
        assertThat(bookingList.size()).isEqualTo(2);
    }

    @Test
    public void shouldReturnOverlapBooking() {
        bookingRepository.save(new Booking(UUID.randomUUID(), 10,
                ZonedDateTime.now().plusDays(1), ZonedDateTime.now().plusDays(2), BookingStatus.BOOKED, "u1", "c1"));

        bookingRepository.save(new Booking(UUID.randomUUID(), 10,
                ZonedDateTime.now().plusDays(3), ZonedDateTime.now().plusDays(4), BookingStatus.BOOKED, "u1", "c1"));

        bookingRepository.save(new Booking(UUID.randomUUID(), 10,
                ZonedDateTime.now().plusDays(5), ZonedDateTime.now().plusDays(6), BookingStatus.BOOKED, "u1", "c1"));

        List<Booking> bookingList = bookingRepository.findOverlapBooking(ZonedDateTime.now().plusDays(1), ZonedDateTime.now().plusDays(4));
        assertThat(bookingList.size()).isEqualTo(2);
    }


}

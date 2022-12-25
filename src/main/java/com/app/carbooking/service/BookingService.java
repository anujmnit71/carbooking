package com.app.carbooking.service;

import com.app.carbooking.common.exception.BookingNotFoundException;
import com.app.carbooking.domain.Booking;
import com.app.carbooking.domain.enumeration.BookingStatus;
import com.app.carbooking.repository.BookingRepository;
import com.app.carbooking.service.dto.BookingDTO;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.validation.constraints.Future;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BookingService implements IBookingService {

    private final BookingRepository bookingRepository;

    private ModelMapper modelMapper;

    public BookingService(BookingRepository bookingRepository, ModelMapper modelMapper) {
        this.bookingRepository = bookingRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public BookingDTO createBooking(BookingDTO bookingRequest) {
        Booking newBooking = modelMapper.map(bookingRequest, Booking.class);
        newBooking.setStatus(BookingStatus.BOOKED);
        Booking booking = bookingRepository.save(newBooking);
        return modelMapper.map(booking, BookingDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public BookingDTO findBookingById(UUID id) {
        Booking booking = bookingRepository.findById(id).orElseThrow(() -> new BookingNotFoundException(id));
        return modelMapper.map(booking, BookingDTO.class);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public BookingDTO updateBooking(UUID id, BookingDTO bookingRequest) {
        Booking booking = bookingRepository.findById(id).orElseThrow(() -> new BookingNotFoundException(id));
        booking.setStartDate(bookingRequest.getStartDate());
        booking.setEndDate(bookingRequest.getEndDate());
        return modelMapper.map(bookingRepository.save(booking), BookingDTO.class);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public BookingDTO cancelBooking(UUID id) {
        Booking booking = bookingRepository.findById(id).orElseThrow(() -> new BookingNotFoundException(id));
        booking.setStatus(BookingStatus.CANCELLED);
        log.info("Cancelled booking with id: {}", id);
        return modelMapper.map(bookingRepository.save(booking), BookingDTO.class);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public BookingDTO pickUpBooking(UUID id) {
        Booking booking = bookingRepository.findById(id).orElseThrow(() -> new BookingNotFoundException(id));
        booking.setStatus(BookingStatus.ONGOING);
        log.info("Pick up booking with id: {}", id);
        return modelMapper.map(bookingRepository.save(booking), BookingDTO.class);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public BookingDTO returnBooking(UUID id) {
        Booking booking = bookingRepository.findById(id).orElseThrow(() -> new BookingNotFoundException(id));
        booking.setStatus(BookingStatus.COMPLETED);
        log.info("Return booking with id: {}", id);
        return modelMapper.map(bookingRepository.save(booking), BookingDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookingDTO> findAll() {
        List<Booking> bookings = bookingRepository.findAll();
        return bookings.stream().map(booking -> modelMapper.map(booking, BookingDTO.class)).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Set<LocalDate> findAvailableDates(@Future LocalDate startDate, @Future LocalDate endDate) {
        Assert.isTrue(startDate.isBefore(endDate), "start date must be before end date");

        List<Booking> overlapBooking = bookingRepository.findOverlapBooking(startDate, endDate);
        log.debug("Overlap bookings: {}", overlapBooking);
        Set<LocalDate> allBookings = startDate.datesUntil(endDate.plusDays(1))
                .collect(Collectors.toCollection(TreeSet::new));
        //Remove all overlapping booking
        overlapBooking.forEach(overlap -> allBookings
                .removeAll(overlap.getStartDate().datesUntil(overlap.getEndDate()).collect(Collectors.toList())));
        log.info("Available bookings: {}", allBookings);
        return allBookings;
    }

}

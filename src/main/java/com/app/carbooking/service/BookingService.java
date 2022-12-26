package com.app.carbooking.service;

import com.app.carbooking.common.exception.BookingNotFoundException;
import com.app.carbooking.common.exception.CarNotFoundException;
import com.app.carbooking.common.exception.InvalidBookingStateException;
import com.app.carbooking.controller.requests.CreateBookingRequest;
import com.app.carbooking.controller.requests.EditBookingRequest;
import com.app.carbooking.domain.Booking;
import com.app.carbooking.domain.Car;
import com.app.carbooking.controller.requests.FindRequest;
import com.app.carbooking.domain.enumeration.BookingStatus;
import com.app.carbooking.repository.BookingRepository;
import com.app.carbooking.repository.CarRepository;
import com.app.carbooking.repository.UserRepository;
import com.app.carbooking.service.dto.BookingDTO;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BookingService implements IBookingService {

    private final BookingRepository bookingRepository;
    private final CarRepository carRepository;
    private final UserRepository userRepository;

    private ModelMapper modelMapper;

    public BookingService(BookingRepository bookingRepository, CarRepository carRepository,
                          UserRepository userRepository, ModelMapper modelMapper) {
        this.bookingRepository = bookingRepository;
        this.carRepository = carRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public BookingDTO createBooking(CreateBookingRequest bookingRequest) {
        Booking newBooking = modelMapper.map(bookingRequest, Booking.class);
        String car_id = bookingRequest.getCar_id();
        Car car = carRepository.findById(car_id).orElseThrow(() -> new CarNotFoundException(car_id));
        int days = (int) Duration.between(bookingRequest.getStartDate(), bookingRequest.getEndDate()).toDays();
        int amount = car.getPricePerDay() * days;
        newBooking.setStatus(BookingStatus.BOOKED);
        newBooking.setAmount(amount);

        newBooking.setUserId(bookingRequest.getUser_id());
        newBooking.setCarId(car_id);
        newBooking.setStartDate(bookingRequest.getStartDate().truncatedTo(ChronoUnit.HOURS));
        newBooking.setEndDate(bookingRequest.getEndDate().truncatedTo(ChronoUnit.HOURS));
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
    public BookingDTO updateBooking(UUID id, EditBookingRequest editBookingRequest) {
        Booking booking = bookingRepository.findById(id).orElseThrow(() -> new BookingNotFoundException(id));
        booking.setStartDate(editBookingRequest.getStartDate());
        booking.setEndDate(editBookingRequest.getEndDate());
        return modelMapper.map(bookingRepository.save(booking), BookingDTO.class);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public BookingDTO cancelBooking(UUID id) {
        Booking booking = bookingRepository.findById(id).orElseThrow(() -> new BookingNotFoundException(id));
        BookingStatus status = booking.getStatus();
        if (!(status.equals(BookingStatus.BOOKED) || status.equals(BookingStatus.CANCELLED))) {
            throw new InvalidBookingStateException(id, status, BookingStatus.CANCELLED);
        }

        booking.setStatus(BookingStatus.CANCELLED);
        log.info("Cancelled booking with id: {}", id);
        return modelMapper.map(bookingRepository.save(booking), BookingDTO.class);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public BookingDTO pickUpBooking(UUID id) {
        Booking booking = bookingRepository.findById(id).orElseThrow(() -> new BookingNotFoundException(id));
        BookingStatus status = booking.getStatus();
        if (!status.equals(BookingStatus.BOOKED)) {
            throw new InvalidBookingStateException(id, status, BookingStatus.ONGOING);
        }
        booking.setStatus(BookingStatus.ONGOING);
        log.info("Pick up booking with id: {}", id);
        return modelMapper.map(bookingRepository.save(booking), BookingDTO.class);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public BookingDTO returnBooking(UUID id) {
        Booking booking = bookingRepository.findById(id).orElseThrow(() -> new BookingNotFoundException(id));
        BookingStatus status = booking.getStatus();
        if (!(status.equals(BookingStatus.ONGOING) || status.equals(BookingStatus.COMPLETED))) {
            throw new InvalidBookingStateException(id, status, BookingStatus.COMPLETED);
        }

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
    public List<Car> findAvailableCars(FindRequest findRequest) {
        ZonedDateTime startDate = findRequest.getStartDate();
        ZonedDateTime endDate = findRequest.getEndDate();
        String model = findRequest.getModel() != null ? findRequest.getModel() : FindRequest.DEFAULT_MODEL;
        int seats = findRequest.getSeats();

        Assert.isTrue(startDate.isBefore(endDate), "start date must be before end date");

        List<Car> availableCars = bookingRepository.findAvailableCars(startDate, endDate, model, seats);
        log.info("Available cars: {}", availableCars);
        return availableCars;
    }


}

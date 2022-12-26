package com.app.carbooking.controller;

import com.app.carbooking.controller.requests.CreateBookingRequest;
import com.app.carbooking.controller.requests.EditBookingRequest;
import com.app.carbooking.domain.Car;
import com.app.carbooking.domain.Slot;
import com.app.carbooking.service.BookingService;
import com.app.carbooking.service.dto.BookingDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/bookings")
@Slf4j
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    /**
     * Book a car
     **/
    @PostMapping
    public ResponseEntity<BookingDTO> createBooking(@RequestBody @Valid CreateBookingRequest bookingRequest) {
        log.debug("Create booking {}", bookingRequest);
        BookingDTO BookingDTO = bookingService.createBooking(bookingRequest);
        return ResponseEntity.created(URI.create("/api/v1/bookings/" + BookingDTO.getBookingId())).body(BookingDTO);
    }

    /**
     * Get Booking details
     **/
    @GetMapping("/{id}")
    public ResponseEntity<BookingDTO> getBooking(@PathVariable("id") UUID id) {
        log.debug("Get booking for id {}", id);
        BookingDTO booking = bookingService.findBookingById(id);
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }

    /**
     * Get all Booking details
     **/
    @GetMapping("/all")
    public ResponseEntity<List<BookingDTO>> getAllBooking() {
        log.debug("Get all bookings");
        List<BookingDTO> bookings = bookingService.findAll();
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    /**
     * Edit a booking start and end date
     **/
    @PutMapping(value = "/{id}")
    public ResponseEntity<BookingDTO> updateBooking(@PathVariable("id") UUID id,
                                                    @RequestBody @Valid EditBookingRequest editBookingRequest) {
        log.debug("Update booking with id {} and payload: {}", id, editBookingRequest);
        BookingDTO BookingDTO = bookingService.updateBooking(id, editBookingRequest);
        return new ResponseEntity<>(BookingDTO, HttpStatus.OK);
    }

    /**
     * Cancel a booking state : BOOKED/CANCELLED -> CANCELLED
     **/
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<BookingDTO> cancelBooking(@PathVariable("id") UUID id) {
        log.debug("Cancel booking for id {}", id);
        BookingDTO bookingDTO = bookingService.cancelBooking(id);
        return new ResponseEntity<>(bookingDTO, HttpStatus.OK);
    }

    /**
     * Pick up car , this will move state : BOOKED -> ONGOING
     **/
    @PutMapping(value = "/pickupCar/{id}")
    public ResponseEntity<BookingDTO> pickUpBooking(@PathVariable("id") UUID id) {
        log.debug("Picking up booking for id {}", id);
        BookingDTO bookingDTO = bookingService.pickUpBooking(id);
        return new ResponseEntity<>(bookingDTO, HttpStatus.OK);
    }

    /**
     * Return car , this will move state : ONGOING/COMPLETED -> COMPLETED
     **/
    @PutMapping(value = "/returnCar/{id}")
    public ResponseEntity<BookingDTO> returnBooking(@PathVariable("id") UUID id) {
        log.debug("Return booking for id {}", id);
        BookingDTO bookingDTO = bookingService.returnBooking(id);
        return new ResponseEntity<>(bookingDTO, HttpStatus.OK);
    }

    /**
     * Return all available cars for a given start and end date
     **/
    @GetMapping("/availableCars")
    public ResponseEntity<List<Car>> getAvailableCars(
            @RequestBody Slot slot) {
        log.debug("Find availabilities cars between {} and {}", slot.getStartDate(), slot.getEndDate());
        return ResponseEntity.ok(bookingService.findAvailableCars(slot.getStartDate(), slot.getEndDate()));
    }

}

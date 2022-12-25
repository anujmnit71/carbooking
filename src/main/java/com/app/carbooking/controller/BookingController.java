package com.app.carbooking.controller;

import com.app.carbooking.controller.requests.CreateBookingRequest;
import com.app.carbooking.controller.requests.EditBookingRequest;
import com.app.carbooking.service.BookingService;
import com.app.carbooking.service.dto.BookingDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/bookings")
@Slf4j
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<BookingDTO> createBooking(@RequestBody @Valid CreateBookingRequest bookingRequest) {
        log.debug("Create booking {}", bookingRequest);
        BookingDTO BookingDTO = bookingService.createBooking(bookingRequest);
        return ResponseEntity.created(URI.create("/api/v1/bookings/" + BookingDTO.getBookingId())).body(BookingDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingDTO> getBooking(@PathVariable("id") UUID id) {
        log.debug("Get booking for id {}", id);
        BookingDTO booking = bookingService.findBookingById(id);
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<BookingDTO>> getAllBooking() {
        log.debug("Get all bookings");
        List<BookingDTO> bookings = bookingService.findAll();
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<BookingDTO> updateBooking(@PathVariable("id") UUID id,
                                                    @RequestBody @Valid EditBookingRequest editBookingRequest) {
        log.debug("Update booking with id {} and payload: {}", id, editBookingRequest);
        BookingDTO BookingDTO = bookingService.updateBooking(id, editBookingRequest);
        return new ResponseEntity<>(BookingDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<BookingDTO> cancelBooking(@PathVariable("id") UUID id) {
        log.debug("Cancel booking for id {}", id);
        BookingDTO bookingDTO = bookingService.cancelBooking(id);
        return new ResponseEntity<>(bookingDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/pickupCar/{id}")
    public ResponseEntity<BookingDTO> pickUpBooking(@PathVariable("id") UUID id) {
        log.debug("Picking up booking for id {}", id);
        BookingDTO bookingDTO = bookingService.pickUpBooking(id);
        return new ResponseEntity<>(bookingDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/returnCar/{id}")
    public ResponseEntity<BookingDTO> returnBooking(@PathVariable("id") UUID id) {
        log.debug("Return booking for id {}", id);
        BookingDTO bookingDTO = bookingService.returnBooking(id);
        return new ResponseEntity<>(bookingDTO, HttpStatus.OK);
    }

    @GetMapping("/availabilities")
    public ResponseEntity<Set<ZonedDateTime>> getReservation(
            @RequestParam ZonedDateTime startDate,
            @RequestParam ZonedDateTime endDate) {
        log.debug("Find availabilities between {} and {}", startDate, endDate);
        return ResponseEntity.ok(bookingService.findAvailableDates(startDate, endDate));
    }
}

package com.app.carbooking.controller;

import com.app.carbooking.service.BookingService;
import com.app.carbooking.service.dto.BookingDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
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
    public ResponseEntity<BookingDTO> createBooking(@RequestBody @Valid BookingDTO bookingRequest) {
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

    @PutMapping(value = "/{id}")
    public ResponseEntity<BookingDTO> updateBooking(@PathVariable("id") UUID id,
                                                    @RequestBody @Valid BookingDTO bookingRequest) {
        log.debug("Update booking with id {} and payload: {}", id, bookingRequest);
        BookingDTO BookingDTO = bookingService.updateBooking(id, bookingRequest);
        return new ResponseEntity<>(BookingDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> cancelBooking(@PathVariable("id") UUID id) {
        log.debug("Cancel booking for id {}", id);
        bookingService.cancelBooking(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/pickup/{id}")
    public ResponseEntity<BookingDTO> pickUpBooking(@PathVariable("id") UUID id) {
        log.debug("Picking up booking for id {}", id);
        BookingDTO bookingDTO = bookingService.pickUpBooking(id);
        return new ResponseEntity<>(bookingDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/return/{id}")
    public ResponseEntity<BookingDTO> returnBooking(@PathVariable("id") UUID id) {
        log.debug("Return booking for id {}", id);
        BookingDTO bookingDTO = bookingService.returnBooking(id);
        return new ResponseEntity<>(bookingDTO, HttpStatus.OK);
    }

    @GetMapping("/availabilities")
    public ResponseEntity<Set<LocalDate>> getReservation(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        log.debug("Find availabilities between {} and {}", startDate, endDate);
        return ResponseEntity.ok(bookingService.findAvailableDates(startDate, endDate));
    }
}

package com.app.carbooking.controller;

import com.app.carbooking.common.exception.InvalidBookingStateException;
import com.app.carbooking.controller.requests.CreateBookingRequest;
import com.app.carbooking.domain.Booking;
import com.app.carbooking.domain.enumeration.BookingStatus;
import com.app.carbooking.service.BookingService;
import com.app.carbooking.service.dto.BookingDTO;
import com.app.carbooking.util.EntityMocksHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@WebMvcTest(BookingController.class)
class BookingControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookingService bookingService;


    @Test
    public void shouldReturn201WhenCreateNewBooking() {
        given(bookingService.createBooking(any(CreateBookingRequest.class))).willReturn(EntityMocksHelper.createBookingDto());
    }

}

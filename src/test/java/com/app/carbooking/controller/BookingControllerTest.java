package com.app.carbooking.controller;

import com.app.carbooking.controller.requests.CreateBookingRequest;
import com.app.carbooking.service.BookingService;
import com.app.carbooking.service.dto.BookingDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

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
        given(bookingService.createBooking(any(CreateBookingRequest.class))).willReturn(new BookingDTO());
    }
}

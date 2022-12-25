package com.app.carbooking;

import com.app.carbooking.controller.requests.CreateBookingRequest;
import com.app.carbooking.domain.Booking;
import com.app.carbooking.repository.BookingRepository;
import com.app.carbooking.service.dto.BookingDTO;
import com.app.carbooking.util.EntityMocksHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Ignore;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.ZonedDateTime;
import java.util.UUID;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
class CarBookingApplicationTests extends AbstractIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void init() {
        this.bookingRepository.deleteAll();
    }

    @AfterEach
    public void cleanUp() {
        this.bookingRepository.deleteAll();
    }

    @Test
    @Ignore
    void shouldReturnSuccessWhenUpdateBooking() throws Exception {

        ZonedDateTime expectedSt = ZonedDateTime.now().plusDays(1);
        ZonedDateTime expectedEnd = ZonedDateTime.now().plusDays(3);

        //given
        Booking bookingReq = EntityMocksHelper.createBooking();
        Booking booking = bookingRepository.save(bookingReq);
        UUID expectedId = booking.getBookingId();
        log.info("Booking id: {}", expectedId);

        BookingDTO bookingDTO = EntityMocksHelper.createBookingDto();
        bookingDTO.setStartDate(expectedSt);
        bookingDTO.setEndDate(expectedEnd);

        mockMvc.perform(
                        put("/api/v1/bookings/{id}", expectedId).content(objectMapper.writeValueAsString(bookingDTO))
                                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bookingId").value(expectedId.toString()));
    }

    @Test
    @Ignore
    void shouldReturn404NotWhenDeleteNotfoundBooking() throws Exception {
        mockMvc.perform(
                        delete("/api/v1/bookings/{id}", UUID.randomUUID()))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", containsString("Can't find booking with id")))
                .andExpect(jsonPath("$.status", is("NOT_FOUND")));
    }

    @Test
    @Ignore
    void shouldReturn200WhenCancelBooking() throws Exception {
        //given
        Booking bookingReq = EntityMocksHelper.createBooking();
        Booking booking = bookingRepository.save(bookingReq);
        UUID expectedId = booking.getBookingId();
        log.info("Booking id: {}", expectedId);

        mockMvc.perform(delete("/api/v1/bookings/{id}", expectedId))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @SneakyThrows
    private MvcResult createNewBooking(CreateBookingRequest request) {
        log.info("Request {}", request);
        MvcResult mvcResult = mockMvc.perform(post("/api/v1/bookings").content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andReturn();
        return mvcResult;
    }


}

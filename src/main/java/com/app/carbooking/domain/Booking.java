package com.app.carbooking.domain;

import com.app.carbooking.domain.enumeration.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * Booking: represents booking of a car by a user.
 */
@Entity
@Table(name = "booking", schema = "rental")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "booking_id")
    private UUID bookingId;

    @NotNull
    @Column(name = "amount")
    private Integer amount;

    @NotNull
    @Column(name = "start_date")
    private ZonedDateTime startDate;

    @NotNull
    @Column(name = "end_date")
    private ZonedDateTime endDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private BookingStatus status;

    @NotNull
    private String userId;

    @NotNull
    private String carId;

}

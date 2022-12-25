package com.app.carbooking.repository;

import com.app.carbooking.domain.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * Spring Data JPA repository for the Booking entity.
 */
@Repository
public interface BookingRepository extends JpaRepository<Booking, UUID> {
    @Query("select b from Booking b "
            + "where b.startDate <= :endDate and b.endDate >= :startDate "
            + "and (b.status = 'BOOKED' or b.status = 'ONGOING') "
            + "order by b.startDate asc ")
    List<Booking> findOverlapBooking(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}

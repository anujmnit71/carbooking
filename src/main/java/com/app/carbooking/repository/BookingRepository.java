package com.app.carbooking.repository;

import com.app.carbooking.domain.Booking;
import com.app.carbooking.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Spring Data JPA repository for the Booking entity.
 */
@Repository
public interface BookingRepository extends JpaRepository<Booking, UUID> {
    @Query(value = """
            select c from Car c
            where c.carId not in
                (
                  select b.carId
                  from Booking b
                  where (b.status = 'BOOKED' or b.status = 'ONGOING')
                  and
                  (b.endDate > :startDate and b.startDate < :endDate)
                 )
            order by pricePerDay asc
            """
    )
    List<Car> findAvailableCars(@Param("startDate") ZonedDateTime startDate, @Param("endDate") ZonedDateTime endDate);
}

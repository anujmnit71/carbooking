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
              select * from booking b
              where b.start_date <= :endDate and b.end_date >= :startDate
              and (b.status = 'BOOKED' or b.status = 'ONGOING')
              order by b.start_date asc
            """,nativeQuery = true)
    List<Booking> findOverlapBooking(@Param("startDate") ZonedDateTime startDate, @Param("endDate") ZonedDateTime endDate);

    @Query(value = """
            select * from car c
            where c.car_id not in
                (
                  select b.car_id
                  from booking b
                  where (b.status = 'BOOKED' or b.status = 'ONGOING')
                  and
                  (b.end_date > :endDate and b.start_date < :startDate)
                 )
            order by price_per_day asc
            """
    ,nativeQuery = false)
    List<Car> findAvailableCars(@Param("startDate") ZonedDateTime startDate, @Param("endDate") ZonedDateTime endDate);
}

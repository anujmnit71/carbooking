package com.app.carbooking.repository;

import com.app.carbooking.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Car entity.
 */
@Repository
public interface CarRepository extends JpaRepository<Car, String> {
}

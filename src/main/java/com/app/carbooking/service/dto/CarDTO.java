package com.app.carbooking.service.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * A DTO for the {@link com.app.carbooking.domain.Car} entity.
 */
@Data
public class CarDTO implements Serializable {

    @NotNull
    private String carId;

    @NotNull
    private Integer pricePerHour;

}

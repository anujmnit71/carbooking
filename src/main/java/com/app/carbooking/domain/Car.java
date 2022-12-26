package com.app.carbooking.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Car
 */

@Entity
@Table(name = "car")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Car implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "car_id", nullable = false)
    private String carId;

    @NotNull
    @Column(name = "price_per_day", nullable = false)
    private Integer pricePerDay;

//    @NotNull
//    @Column(name = "car_license_no", nullable = false)
//    private String carLicenseNo;
//
//    @NotNull
//    @Column(name = "kms_driven", nullable = false)
//    private Integer kmsDriven;
//
//    @NotNull
//    @Column(name = "seats", nullable = false)
//    private Integer seats;
//
//    @NotNull
//    @Enumerated(EnumType.STRING)
//    @Column(name = "model", nullable = false)
//    private Model model;
//
//    @NotNull
//    @Enumerated(EnumType.STRING)
//    @Column(name = "transmission", nullable = false)
//    private Transmission transmission;
//
//    @NotNull
//    @Enumerated(EnumType.STRING)
//    @Column(name = "fuel", nullable = false)
//    private Fuel fuel;
}

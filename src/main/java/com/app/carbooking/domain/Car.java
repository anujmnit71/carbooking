package com.app.carbooking.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Car
 */

@Entity
@Table(name = "car", schema = "rental")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "car_id", nullable = false)
    private String carId;

    @NotNull
    @Column(name = "price_per_hour", nullable = false)
    private Integer pricePerHour;

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

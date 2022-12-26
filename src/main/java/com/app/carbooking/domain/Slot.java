package com.app.carbooking.domain;

import lombok.Data;
import lombok.ToString;

import java.time.ZonedDateTime;

/**
 * Slot
 */
@Data
@ToString
public class Slot {
    private ZonedDateTime startDate;
    private ZonedDateTime endDate;
}

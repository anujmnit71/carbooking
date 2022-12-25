package com.app.carbooking.controller.requests;

import java.time.ZonedDateTime;

public interface BookingRequestBody {
    ZonedDateTime getStartDate();
    ZonedDateTime getEndDate();
}

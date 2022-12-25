package com.app.carbooking.common.validation;

import com.app.carbooking.controller.requests.BookingRequestBody;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

@Slf4j
public class StartEndDateValidator implements ConstraintValidator<ValidStartEndDate, BookingRequestBody> {

    @Override
    public boolean isValid(BookingRequestBody bookingReq, ConstraintValidatorContext constraintValidatorContext) {
        if (Objects.isNull(bookingReq)) {
            return false;
        }
        log.debug("Start date: {}, end date: {}", bookingReq.getStartDate(), bookingReq.getEndDate());
        return bookingReq.getStartDate().isBefore(bookingReq.getEndDate());
    }
}

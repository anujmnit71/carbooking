package com.app.carbooking.common.validation;

import com.app.carbooking.service.dto.BookingDTO;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

@Slf4j
public class StartEndDateValidator implements ConstraintValidator<ValidStartEndDate, BookingDTO> {

    @Override
    public boolean isValid(BookingDTO booking, ConstraintValidatorContext constraintValidatorContext) {
        if (Objects.isNull(booking)) {
            return false;
        }
        log.debug("Start date: {}, end date: {}", booking.getStartDate(), booking.getEndDate());
        return booking.getStartDate().isBefore(booking.getEndDate());
    }
}

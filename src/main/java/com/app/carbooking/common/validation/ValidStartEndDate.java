package com.app.carbooking.common.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(value = {TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = {StartEndDateValidator.class})
@Documented
public @interface ValidStartEndDate {
    String message() default "start date should be before end date";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

package com.practice.cryptotrading.constraint;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;

/**
 * @author Sin Yee
 *
 */

@Constraint(validatedBy = { WhiteListValidator.class })
@Documented
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR,
		ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@ReportAsSingleViolation
public @interface WhiteList {

	String message() default "invalid value";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String[] value() default {};
}

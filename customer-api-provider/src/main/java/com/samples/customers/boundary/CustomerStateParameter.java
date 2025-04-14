package com.samples.customers.boundary;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;
import jakarta.validation.constraints.Pattern;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({
  ElementType.METHOD,
  ElementType.FIELD,
  ElementType.ANNOTATION_TYPE,
  ElementType.CONSTRUCTOR,
  ElementType.PARAMETER
})
@Retention(RetentionPolicy.RUNTIME)
@Documented
// Bean Validation
@Constraint(validatedBy = {})
@Pattern(regexp = "active|locked|disabled")
@ReportAsSingleViolation
public @interface CustomerStateParameter {

  String message() default "must be one of 'active', 'locked', 'disabled'";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

}

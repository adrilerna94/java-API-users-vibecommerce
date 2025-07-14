package com.adriauson.vibecommerce.vibecommerce.validator;

import jakarta.validation.Constraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Size(min=3, max=30)

public @interface ValidLastName {
    String message() default "Invalid Last Name";
    Class<?>[] groups() default {};
    Class< ? extends  jakarta.validation.Payload>[] payload() default  {};
}
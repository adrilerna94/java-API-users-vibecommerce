package com.adriauson.vibecommerce.validation.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.constraints.Size;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Size(min=10, max=250, message= "Address size must be between 10 to 250 characters")

public @interface ValidAddress {
    String message() default "Invalid Address";
    Class<?>[] groups() default {};
    Class< ? extends  jakarta.validation.Payload>[] payload() default  {};
}
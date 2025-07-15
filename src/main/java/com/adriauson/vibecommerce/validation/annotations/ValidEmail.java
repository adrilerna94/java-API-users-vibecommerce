package com.adriauson.vibecommerce.validation.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.constraints.Email;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {}) // Sin clase validadora personalizada
@Target({ElementType.FIELD})
@Retention (RetentionPolicy.RUNTIME)
@Email(message = "Must be a valid email")

public @interface ValidEmail {
    String message() default "Invalid email";
    Class<?>[] groups() default {};
    Class< ? extends  jakarta.validation.Payload>[] payload() default  {};
}

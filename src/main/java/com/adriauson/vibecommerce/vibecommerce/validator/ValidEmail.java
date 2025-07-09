package com.adriauson.vibecommerce.vibecommerce.validator;

import jakarta.validation.Constraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {}) // Sin clase validadora personalizada
@Target({ElementType.FIELD})
@Retention (RetentionPolicy.RUNTIME)
@Email(message = "Must be a valid email")
@NotBlank(message = "Email is required")

public @interface ValidEmail {
    String message() default "Invalid email";
    Class<?>[] groups() default {};
    Class< ? extends  jakarta.validation.Payload>[] payload() default  {};
}

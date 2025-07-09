package com.adriauson.vibecommerce.vibecommerce.validator;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

// Está interfaz será utilizada por la Clase PasswordValidator
@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({ ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPassword {
    String message() default "Password must contain at least one uppercase, one lowercase, and be at least 5 characters long";
    Class<?>[] groups() default {};
    Class <? extends Payload>[] payload() default {};
}

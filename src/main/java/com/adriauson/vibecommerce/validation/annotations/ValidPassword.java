package com.adriauson.vibecommerce.validation.annotations;


import com.adriauson.vibecommerce.validation.validators.PasswordValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({ ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)

public @interface ValidPassword {
    String message() default "Password must contain at least one uppercase, one lowercase, and be at least 5 characters long";
    Class<?>[] groups() default {};
    Class <? extends Payload>[] payload() default {};
}

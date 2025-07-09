package com.adriauson.vibecommerce.vibecommerce.validator;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({ ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPassword {
    String message() default "Password must contain at leat one uppercase, one lowercase, and be at least 5 characters long";
    Class<?>[] groups() default {};
    Class <? extends Payload>[] payload() default {};
}

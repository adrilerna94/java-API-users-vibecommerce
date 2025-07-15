package com.adriauson.vibecommerce.validation.validators;

import com.adriauson.vibecommerce.validation.annotations.ValidPassword;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

// CustomValidator

public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {
    private static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z]).{5,}$";

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null || password.trim().isEmpty()) {
            setMessage(context, "Password is required");
            return false;

        }
        if (password.length() < 5) {
            setMessage(context, "Password must be at least 5 characters long");
            return false;
        }
        if (!password.matches(PASSWORD_REGEX)){
            setMessage(context, "Password must contain at least one uppercase and one lowercase letter");
            return false;
        }
        return true;
    }

    // Method to create custom messages
    private void setMessage(ConstraintValidatorContext context, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation();
    }
}

package com.adriauson.vibecommerce.validation.validators;

import com.adriauson.vibecommerce.dto.RegisterUserDto;
import com.adriauson.vibecommerce.validation.annotations.PasswordsMatch;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordsMatchValidator implements ConstraintValidator<PasswordsMatch, RegisterUserDto> {
    @Override
    public boolean isValid(RegisterUserDto registerDto, ConstraintValidatorContext context) {
        if (registerDto.getPassword() == null || registerDto.getConfirmPassword() == null) {
            return false;
        }
        boolean passwordMatch = registerDto.getPassword().equals(registerDto.getConfirmPassword());
        return passwordMatch;
    }
}

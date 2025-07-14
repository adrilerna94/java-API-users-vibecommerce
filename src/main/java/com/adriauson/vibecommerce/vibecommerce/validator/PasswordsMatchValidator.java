package com.adriauson.vibecommerce.vibecommerce.validator;

import com.adriauson.vibecommerce.vibecommerce.dto.RegisterUserDto;
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

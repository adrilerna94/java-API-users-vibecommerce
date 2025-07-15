package com.adriauson.vibecommerce.validation.validators;

import com.adriauson.vibecommerce.dto.UpdateUserDto;
import com.adriauson.vibecommerce.validation.annotations.AtLeastOneRegisterField;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidUpdatedUser implements ConstraintValidator<AtLeastOneRegisterField, UpdateUserDto> {
    @Override
    public boolean isValid(UpdateUserDto dto, ConstraintValidatorContext context) {
        return dto.getFirstName() != null ||
                dto.getLastName() != null ||
                dto.getEmail() != null ||
                dto.getPassword() != null ||
                dto.getAddress() != null;
    }
}

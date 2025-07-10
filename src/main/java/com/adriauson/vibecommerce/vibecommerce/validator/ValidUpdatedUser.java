package com.adriauson.vibecommerce.vibecommerce.validator;

import com.adriauson.vibecommerce.vibecommerce.dto.UpdateUserDto;
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

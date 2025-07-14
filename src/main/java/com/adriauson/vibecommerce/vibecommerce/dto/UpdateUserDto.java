package com.adriauson.vibecommerce.vibecommerce.dto;

import com.adriauson.vibecommerce.vibecommerce.dto.BaseUserDto;
import com.adriauson.vibecommerce.vibecommerce.validator.AtLeastOneRegisterField;
import com.adriauson.vibecommerce.vibecommerce.validator.ValidPassword;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AtLeastOneRegisterField
public class UpdateUserDto extends BaseUserDto {
}
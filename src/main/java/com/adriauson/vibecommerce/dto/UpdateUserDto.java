package com.adriauson.vibecommerce.dto;

import com.adriauson.vibecommerce.validation.annotations.AtLeastOneRegisterField;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AtLeastOneRegisterField
public class UpdateUserDto extends BaseUserDto {
}
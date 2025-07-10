package com.adriauson.vibecommerce.vibecommerce.dto;

import com.adriauson.vibecommerce.vibecommerce.dto.BaseUserDto;
import com.adriauson.vibecommerce.vibecommerce.validator.ValidPassword;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserDto extends BaseUserDto {

    @ValidPassword
    private String password;
}
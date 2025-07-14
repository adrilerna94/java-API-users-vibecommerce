package com.adriauson.vibecommerce.vibecommerce.dto;

import com.adriauson.vibecommerce.vibecommerce.dto.BaseUserDto;
import com.adriauson.vibecommerce.vibecommerce.validation.groups.OnCreate;
import com.adriauson.vibecommerce.vibecommerce.validation.groups.OnUpdate;
import com.adriauson.vibecommerce.vibecommerce.validator.PasswordsMatch;
import com.adriauson.vibecommerce.vibecommerce.validator.ValidPassword;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@PasswordsMatch
public class RegisterUserDto extends BaseUserDto {

    @NotBlank(message = "Password confirmation is required", groups = OnCreate.class)
    @ValidPassword(groups = {OnCreate.class, OnUpdate.class})
    private String confirmPassword;
}
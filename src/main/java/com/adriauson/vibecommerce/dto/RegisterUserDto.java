package com.adriauson.vibecommerce.dto;

import com.adriauson.vibecommerce.validation.groups.OnCreate;
import com.adriauson.vibecommerce.validation.groups.OnUpdate;
import com.adriauson.vibecommerce.validation.annotations.PasswordsMatch;
import com.adriauson.vibecommerce.validation.annotations.ValidPassword;

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
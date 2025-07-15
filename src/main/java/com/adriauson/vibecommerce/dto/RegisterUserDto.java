package com.adriauson.vibecommerce.dto;

import com.adriauson.vibecommerce.validation.groups.OnCreate;
import com.adriauson.vibecommerce.validation.groups.OnUpdate;
import com.adriauson.vibecommerce.validation.annotations.PasswordsMatch;
import com.adriauson.vibecommerce.validation.annotations.ValidPassword;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * 📦 DTO for registering a new user.
 * 🔒 Includes password confirmation for validation (matched via custom annotation).
 */
@Getter
@Setter
@PasswordsMatch
@Schema(name = "RegisterUserDto", description = "Data required to register a new user, including password confirmation.")
public class RegisterUserDto extends BaseUserDto {

    @Schema(
            description = "Password confirmation (must match the original password)",
            example = "MyPass123",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Password confirmation is required", groups = OnCreate.class)
    @ValidPassword(groups = {OnCreate.class, OnUpdate.class})
    private String confirmPassword;
}

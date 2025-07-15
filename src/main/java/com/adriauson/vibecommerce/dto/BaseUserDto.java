package com.adriauson.vibecommerce.dto;

import com.adriauson.vibecommerce.validation.annotations.*;
import com.adriauson.vibecommerce.validation.groups.OnCreate;
import com.adriauson.vibecommerce.validation.groups.OnUpdate;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * 📌 Base DTO shared by: RegisterUserDto, UpdateUserDto, UserDto
 * 🧱 Applies the "O" from SOLID — Open for Extension, Closed for Modification.
 */
@Getter
@Setter
public abstract class BaseUserDto {

    @Schema(
            description = "User's first name",
            example = "Alice",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Firstname is required", groups = OnCreate.class)
    @ValidFirstName(groups = {OnCreate.class, OnUpdate.class})
    private String firstName;

    @Schema(
            description = "User's last name",
            example = "Johnson",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Lastname is required", groups = OnCreate.class)
    @ValidLastName(groups = {OnCreate.class, OnUpdate.class})
    private String lastName;

    @Schema(
            description = "User email address",
            example = "alice.johnson@example.com",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Email is required", groups = OnCreate.class)
    @ValidEmail(groups = {OnCreate.class, OnUpdate.class})
    private String email;

    @Schema(
            description = "User's physical address",
            example = "123 Main Street, New York, NY",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Address is required", groups = OnCreate.class)
    @ValidAddress(groups = {OnCreate.class, OnUpdate.class})
    private String address;

    @Schema(
            description = "User's password. Must be (min 5 chars, upper/lower)",
            example = "MyPass123 ",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Password is required", groups = OnCreate.class)
    @ValidPassword(groups = {OnCreate.class, OnUpdate.class})
    private String password;
}


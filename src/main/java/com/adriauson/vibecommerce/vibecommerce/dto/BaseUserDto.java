package com.adriauson.vibecommerce.vibecommerce.dto;

import com.adriauson.vibecommerce.vibecommerce.validation.groups.OnCreate;
import com.adriauson.vibecommerce.vibecommerce.validation.groups.OnUpdate;
import com.adriauson.vibecommerce.vibecommerce.validator.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

// aplicamos la O de SOLID: Open to Extension- Close to Modification
/*
    * clase abstracta BaseUserDto con
    * propiedades comunes a las clases Dtos que extenderán de esta clase:
    * UserDto, RegisterUserDto, UpdateUserDto
*/


@Getter
@Setter
public abstract class BaseUserDto {
    @NotBlank(message = "Firstname is required", groups = OnCreate.class)
    @ValidFirstName(groups = {OnCreate.class, OnUpdate.class})
    private String firstName;

    @NotBlank(message= "Lastname is required", groups = OnCreate.class)
    @ValidLastName(groups = {OnCreate.class, OnUpdate.class})
    private String lastName;

    @NotBlank(message = "Email is required", groups = OnCreate.class)
    @ValidEmail(groups = {OnCreate.class, OnUpdate.class})
    private String email;

    @NotBlank(message= "Address is required", groups = OnCreate.class)
    @ValidAddress(groups = {OnCreate.class, OnUpdate.class})
    private String address;

    @NotBlank(message = "Password is requited", groups = OnCreate.class)
    @ValidPassword(groups = {OnCreate.class, OnUpdate.class})
    private String password;
}

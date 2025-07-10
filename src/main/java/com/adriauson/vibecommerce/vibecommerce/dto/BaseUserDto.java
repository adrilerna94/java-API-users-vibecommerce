package com.adriauson.vibecommerce.vibecommerce.dto;

import com.adriauson.vibecommerce.vibecommerce.validator.*;
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
    @ValidFirstName
    private String firstName;

    @ValidLastName
    private String lastName;

    @ValidEmail
    private String email;

    @ValidAddress
    private String address;
}

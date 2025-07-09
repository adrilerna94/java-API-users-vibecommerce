package com.adriauson.vibecommerce.vibecommerce.dto;
import com.adriauson.vibecommerce.vibecommerce.validator.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ValidFirstName
    private String firstName;

    @ValidLastName
    private String lastName;

    @ValidEmail
    private String email;

    @ValidPassword
    private String password;

    @ValidAddress
    private String address;
}

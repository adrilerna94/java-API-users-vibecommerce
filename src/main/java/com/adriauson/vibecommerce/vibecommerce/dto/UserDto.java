package com.adriauson.vibecommerce.vibecommerce.dto;
import com.adriauson.vibecommerce.vibecommerce.validator.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto extends BaseUserDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}

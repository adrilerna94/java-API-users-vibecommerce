package com.adriauson.vibecommerce.vibecommerce.service;

import com.adriauson.vibecommerce.vibecommerce.dto.RegisterUserDto;
import com.adriauson.vibecommerce.vibecommerce.dto.UpdateUserDto;
import com.adriauson.vibecommerce.vibecommerce.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers ();
    UserDto getUserById (Long id);
    UserDto updateUser(Long id, UpdateUserDto updateDto);
    UserDto registerUser (RegisterUserDto registerDto);
    void deleteUser (Long id);
}

package com.adriauson.vibecommerce.service;

import com.adriauson.vibecommerce.dto.RegisterUserDto;
import com.adriauson.vibecommerce.dto.UpdateUserDto;
import com.adriauson.vibecommerce.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto getUserById (Long id);
    List<UserDto> getAllUsers ();
    UserDto registerUser (RegisterUserDto registerDto);
    UserDto updateUser(Long id, UpdateUserDto updateDto);
    void deleteUser (Long id);
}

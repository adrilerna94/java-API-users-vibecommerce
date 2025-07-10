package com.adriauson.vibecommerce.vibecommerce.mapper;

import com.adriauson.vibecommerce.vibecommerce.dto.RegisterUserDto;
import com.adriauson.vibecommerce.vibecommerce.dto.UpdateUserDto;
import com.adriauson.vibecommerce.vibecommerce.dto.UserDto;
import com.adriauson.vibecommerce.vibecommerce.model.User;

// podía haber usado modelMapper para hacer el mapeo automático
// De momento lo hago así para entender mejor el mapeo

public class UserMapper {

    public static UserDto mapUserToUserDto(User user){

        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());

        // en casos normales no mostraríamos la contraseña ni *****
        // lo hacemos a modo de ejemplo
        userDto.setPassword("*****");
        userDto.setEmail(user.getEmail());
        userDto.setAddress(user.getAddress());

        return userDto;

    }

    public static User mapRegisterDtoToUser(RegisterUserDto registerDto){

        User user = new User();

        user.setFirstName(registerDto.getFirstName());
        user.setLastName(registerDto.getLastName());

        // aqui hashearíamos la contraseña antes de guardarla
        user.setPassword(registerDto.getPassword());
        user.setEmail(registerDto.getEmail());
        user.setAddress(registerDto.getAddress());

        return user;

    }

    // no devolvemos nada --> ya que trabajamos con el objeto original. Modifica por referencia no por copia.
    public static void mapUpdateUserDtoToExistingUser(UpdateUserDto updateDto, User user){

        if (updateDto.getFirstName() != null) {
            user.setFirstName(updateDto.getFirstName());
        }

        if (updateDto.getLastName() != null){
            user.setLastName(updateDto.getLastName());
        }

        // Aqui hashearíamos la password
        if (updateDto.getPassword() != null){
            user.setPassword(updateDto.getPassword());
        }
        if (updateDto.getEmail() != null){
            user.setEmail(updateDto.getEmail());
        }

        if (updateDto.getAddress() != null){
            user.setAddress(updateDto.getAddress());
        }

    }
}

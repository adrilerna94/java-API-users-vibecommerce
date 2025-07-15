package com.adriauson.vibecommerce.mapper;

import com.adriauson.vibecommerce.dto.RegisterUserDto;
import com.adriauson.vibecommerce.dto.UpdateUserDto;
import com.adriauson.vibecommerce.dto.UserDto;
import com.adriauson.vibecommerce.entity.User;
import org.springframework.stereotype.Component;

// podía haber usado modelMapper para hacer el mapeo automático
// De momento lo hago así para entender mejor el mapeo


/*
 * 🟡 ADVERTENCIA: "Static member accessed via instance reference"
 *
 * ¿Qué significa?
 * --------------------------------------------------
 * Este mensaje aparece cuando estás llamando un método `static` como si fuera un método de instancia.
 *
 * Ejemplo que genera el warning:
 *     userMapper.mapRegisterDtoToUser(dto);  // INCORRECTO (si el método es static)
 *
 * Java lo permite, pero es incorrecto desde el punto de vista del diseño.
 * La forma correcta de invocar un método estático es usando directamente el nombre de la clase:
 *
 *     UserMapper.mapRegisterDtoToUser(dto);  // CORRECTO
 *
 * --------------------------------------------------
 * ¿Qué opciones tengo?
 *
 * ✅ Opción 1: Mantener los métodos como `static`
 * - Es apropiado si `UserMapper` es una clase puramente utilitaria (sin estado).
 * - No hace falta inyectarla como dependencia.
 * - Elimina cualquier instancia inyectada (`@Autowired`) de UserMapper.
 *
 * Ejemplo:
 *     public class UserMapper {
 *         public static User mapRegisterDtoToUser(RegisterUserDto dto) { ... }
 *     }
 *
 *     // Llamada desde cualquier lugar:
 *     User user = UserMapper.mapRegisterDtoToUser(dto);
 *
 * ✅ Opción 2: Convertir `UserMapper` en un componente Spring inyectable (no static)
 * - Es la mejor práctica si quieres seguir un diseño orientado a objetos limpio y flexible.
 * - Permite pruebas más sencillas y separación clara de responsabilidades.
 *
 * Pasos:
 * 1. Eliminar `static` de los métodos del mapper.
 * 2. Anotar la clase con `@Component`.
 * 3. Inyectarla con constructor injection.
 *
 * Ejemplo:
 *     @Component
 *     public class UserMapper {
 *         public User mapRegisterDtoToUser(RegisterUserDto dto) { ... }
 *     }
 *
 *     @Service
 *     public class UserServiceImpl implements UserService {
 *         private final UserMapper userMapper;
 *
 *         public UserServiceImpl(UserRepository repo, UserMapper userMapper) {
 *             this.userRepository = repo;
 *             this.userMapper = userMapper;
 *         }
 *
 *         public void register(RegisterUserDto dto) {
 *             User user = userMapper.mapRegisterDtoToUser(dto);
 *         }
 *     }
 *
 * --------------------------------------------------
 * ✅ Recomendación para aprendizaje:
 * Si estás empezando y quieres aprender bien, utiliza la opción 2.
 * Te ayudará a entender mejor cómo funciona Spring, los beans y la inyección de dependencias.
 */


/*
La anotación @Component en Spring marca una clase como un componente gestionado por el contenedor de Spring.
Es una de las anotaciones clave para habilitar la inyección de dependencias automática.
*/

@Component
public class UserMapper {

    public UserDto mapUserToUserDto(User user){

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

    public User mapRegisterDtoToUser(RegisterUserDto registerDto){

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
    public void mapUpdateUserDtoToExistingUser(UpdateUserDto updateDto, User user){

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

package com.adriauson.vibecommerce.vibecommerce.service.impl;

import com.adriauson.vibecommerce.vibecommerce.dto.RegisterUserDto;
import com.adriauson.vibecommerce.vibecommerce.dto.UpdateUserDto;
import com.adriauson.vibecommerce.vibecommerce.dto.UserDto;
import com.adriauson.vibecommerce.vibecommerce.exception.EmailAlreadyExistsException;
import com.adriauson.vibecommerce.vibecommerce.exception.UserNoSuchElementException;
import com.adriauson.vibecommerce.vibecommerce.mapper.UserMapper;
import com.adriauson.vibecommerce.vibecommerce.entity.User;
import com.adriauson.vibecommerce.vibecommerce.repository.UserRepository;
import com.adriauson.vibecommerce.vibecommerce.service.UserService;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;

/*
 * ✅ UserServiceImpl - implementación del servicio de usuarios.
 *
 * 👉 Esta clase utiliza **constructor injection manual**, la forma recomendada
 * de inyección de dependencias en Spring Boot por claridad, testabilidad y adherencia
 * a los principios SOLID (especialmente Dependency Inversion).
 *
 * 📌 Uso de `private final` en las dependencias:
 * - `final` indica que estas variables deben ser inicializadas una vez en el constructor
 *   y no pueden ser modificadas después (inmutabilidad).
 * - Esto hace que las dependencias sean más seguras, evita errores y facilita los tests.
 *
 * 📌 Inyección por constructor:
 * - Spring detecta automáticamente este constructor y lo usa para inyectar los beans
 *   si hay un único constructor (no se necesita `@Autowired`).
 * - Este enfoque es limpio, explícito y favorece el diseño orientado a objetos.
 *
 * 🔁 ¿Cómo se haría lo mismo con Lombok?
 * Si deseas escribir menos código sin perder buenas prácticas, puedes usar:
 *
 *     @Service
 *     @RequiredArgsConstructor
 *     public class UserServiceImpl implements UserService {
 *         private final UserRepository userRepository;
 *         private final UserMapper userMapper;
 *     }
 *
 * - `@RequiredArgsConstructor` genera automáticamente un constructor
 *   con todos los campos `final` o `@NonNull`.
 * - Spring usará ese constructor para la inyección.
 * - Requiere tener Lombok como dependencia en el proyecto.
 *
 * 🧠 Recomendación:
 * Usa primero constructor injection manual como estás haciendo aquí
 * para aprender cómo funciona Spring internamente.
 * Más adelante, cuando lo domines, puedes migrar a Lombok si quieres reducir el boilerplate.
 */

/**
 * @Service indica que esta clase es un componente de servicio (lógica de negocio),
 * gestionado por Spring y disponible para inyección en otras clases.
 */

@Service
public class UserServiceImpl implements UserService {

    private final MessageSource messageSource;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

     public UserServiceImpl (UserRepository userRepository, UserMapper userMapper, MessageSource messageSource) {
         this.userRepository = userRepository;
         this.userMapper = userMapper;
         this.messageSource = messageSource;
     }
     @Override
     public UserDto registerUser(RegisterUserDto registerDto) {

         // validamos email duplicado
         if (this.userRepository.existsByEmail(registerDto.getEmail())) {
             throw new EmailAlreadyExistsException("Email is already in use");
         }

         // mapeo de DTO a entidad
         User user = this.userMapper.mapRegisterDtoToUser(registerDto);

         // persistencia en la BBDD
         userRepository.save(user);

         // mapeo de vuelta a DTO de respuesta
         return this.userMapper.mapUserToUserDto(user);
     }
     
     @Override
     public List<UserDto> getAllUsers () {
         // recuperamos todos los usuarios de la DB
         List<User> userList = this.userRepository.findAll();
        
         // generamos lista userDto vacía.
         List<UserDto> userListDto = new ArrayList<>();

         // Recorremos cada elemento de la lista de users
         // mapeamos cada user a userDto
         // añadimos a la lista vacia de userDto
         for (User user : userList) {
            UserDto userDto = this.userMapper.mapUserToUserDto(user);
            userListDto.add(userDto);
         }
         return userListDto;
     }

    /*
     * 📌 getUserById:
     * - Busca un usuario por ID usando findById()
     * - Si no se encuentra, lanza una excepción personalizada UserNoSuchElementException
     * - El mensaje se obtiene de MessageSource para internacionalización
     *
     * 📌 GlobalExceptionHandler:
     * - Captura UserNoSuchElementException y devuelve un error limpio 404 Not Found
     *
     * 💡 Recomendación: usar Optional.orElseThrow() en lugar de try-catch para código más limpio
     */
    /*
     * ❌ Optional.get() es riesgoso: lanza NoSuchElementException si no hay valor.
     *
     * ✅ Es mejor usar Optional.orElseThrow(), que lanza una excepción personalizada
     *     si el valor no está presente. Es más seguro y limpio.
     *
     * Ejemplo recomendado:
     * userRepository.findById(id)
     *     .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));
     *
     * Esto evita errores inesperados y sigue buenas prácticas con Optional en Java.
     */

    @Override
     public UserDto getUserById (Long id) {

         // OPTION clássica --> manejar con try/catch si no se encuentra el elemento
         /*
         try {
             // recuperamos user por id
             User user = this.userRepository.findById(id).get();
             return this.userMapper.mapUserToUserDto(user);
         } catch (NoSuchElementException exception){
            String message = messageSource.getMessage("entity.not.found", new Object[]{id}, Locale.getDefault());
            throw new UserNoSuchElementException(message, id);
         }
         */

         // 💡 API Optional ➡️ Recomendado Docu Oficial
         User user = userRepository.findById(id)
                 .orElseThrow(() ->  {
                     String message = this.messageSource.getMessage("entity.not.found", new Object[]{id}, Locale.getDefault());
                     return new UserNoSuchElementException(message, id);
                 });
         return this.userMapper.mapUserToUserDto(user);

     }

     public UserDto updateUser (Long id, UpdateUserDto updateDto){

        // 1️⃣ Buscar el usuario existente
        User existingUser = userRepository.findById(id)
                .orElseThrow(() ->  {
                     String message = this.messageSource.getMessage("entity.not.found", new Object[]{id}, Locale.getDefault());
                     return new UserNoSuchElementException(message, id);
                });

        // 2️⃣ Actualizamos solo campos no nulos del DTO
        this.userMapper.mapUpdateUserDtoToExistingUser(updateDto, existingUser);

        // 3️⃣ Guardamos en la base de datos
        this.userRepository.save(existingUser);

        // 4️⃣ Devolvemos el resultado actualizado como DTO
        return this.userMapper.mapUserToUserDto(existingUser);

     }

     public void deleteUser (Long id) {
         User existingUser = userRepository.findById(id)
                 .orElseThrow(() ->  {
                     String message = this.messageSource.getMessage("entity.not.found", new Object[]{id}, Locale.getDefault());
                     return new UserNoSuchElementException(message, id);
                 });
        this.userRepository.deleteById(id);
     }


}

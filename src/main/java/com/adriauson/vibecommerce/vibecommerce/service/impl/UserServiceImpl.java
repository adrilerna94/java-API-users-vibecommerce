package com.adriauson.vibecommerce.vibecommerce.service.impl;

import com.adriauson.vibecommerce.vibecommerce.dto.RegisterUserDto;
import com.adriauson.vibecommerce.vibecommerce.dto.UserDto;
import com.adriauson.vibecommerce.vibecommerce.mapper.UserMapper;
import com.adriauson.vibecommerce.vibecommerce.entity.User;
import com.adriauson.vibecommerce.vibecommerce.repository.UserRepository;
import com.adriauson.vibecommerce.vibecommerce.service.UserService;
import org.springframework.stereotype.Service;

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

     private final UserRepository userRepository;
     private final UserMapper userMapper;

     public UserServiceImpl (UserRepository userRepository, UserMapper userMapper) {
         this.userRepository = userRepository;
         this.userMapper = userMapper;
     }
     @Override
     public UserDto registerUser(RegisterUserDto registerDto) {

         // validamos email duplicado
         if (this.userRepository.existsByEmail(registerDto.getEmail())) {
             throw new IllegalArgumentException("Email is already in use");
         }

         // mapeo de DTO a entidad
         User user = this.userMapper.mapRegisterDtoToUser(registerDto);

         // persistencia en la BBDD
         userRepository.save(user);

         // mapeo de vuelta a DTO de respuesta
         return this.userMapper.mapUserToUserDto(user);
     }

}

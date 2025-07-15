package com.adriauson.vibecommerce.service.impl;

import com.adriauson.vibecommerce.dto.RegisterUserDto;
import com.adriauson.vibecommerce.dto.UpdateUserDto;
import com.adriauson.vibecommerce.dto.UserDto;
import com.adriauson.vibecommerce.exception.EmailAlreadyExistsException;
import com.adriauson.vibecommerce.exception.UserNotFoundException;
import com.adriauson.vibecommerce.mapper.UserMapper;
import com.adriauson.vibecommerce.entity.User;
import com.adriauson.vibecommerce.repository.UserRepository;
import com.adriauson.vibecommerce.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

// 🧩 Con Lombok:
// @RequiredArgsConstructor genera constructor con todos los campos final
// Requiere tener Lombok instalad
@Service
public class UserServiceImpl implements UserService {

    /* 📌 Uso de final en las dependencias:
     * - final indica que estas variables deben ser inicializadas una vez en el constructor
     *   y no pueden ser modificadas después (inmutabilidad).
     * - Esto hace que las dependencias sean más seguras, evita errores y facilita los tests.
     */
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final MessageSource messageSource;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    // ✔️ Constructor injection (recomendado)
    // - Evita el uso de @Autowired
    // - Asegura inmutabilidad con `private final`
    // - Facilita testeo y mantenimiento

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, MessageSource messageSource) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.messageSource = messageSource;
    }

    @Override
    public UserDto registerUser(RegisterUserDto registerDto) {
        logger.info("Attempting to register user with email: {}", registerDto.getEmail());

        if (this.userRepository.existsByEmail(registerDto.getEmail())) {
            logger.warn("Email already in use: {}", registerDto.getEmail());
            throw new EmailAlreadyExistsException("Email is already in use");
        }

        User user = this.userMapper.mapRegisterDtoToUser(registerDto);
        userRepository.save(user);
        logger.info("User successfully registered with email: {}", registerDto.getEmail());

        return this.userMapper.mapUserToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        logger.info("Fetching all users from database");
        List<User> userList = this.userRepository.findAll();
        logger.debug("Total users found: {}", userList.size());

        List<UserDto> userListDto = new ArrayList<>();
        for (User user : userList) {
            UserDto userDto = this.userMapper.mapUserToUserDto(user);
            userListDto.add(userDto);
        }

        return userListDto;
    }

    @Override
    public UserDto getUserById(Long id) {

        // ❌ No recomendado:
        // User user = userRepository.findById(id).get(); // Puede lanzar excepción si no hay valor

        // ✅ Recomendado:
        // - Evita errores inesperados
        // - Mejora legibilidad
        // - Compatible con manejo global de excepciones
        logger.info("Fetching user with ID: {}", id);

        // ✅ Manejo de errores con orElseThrow en vez de try-catch
        // ✅ Separación clara entre lógica de persistencia, mapping y DTOs
        // ✅ Logging añadido para trazabilidad
        // ✅ `@Service` para indicar que es un componente de lógica de negocio


        User user = userRepository.findById(id)
                .orElseThrow(() -> {
                    String message = this.messageSource.getMessage("entity.not.found", new Object[]{id}, Locale.getDefault());
                    logger.error("User not found with ID: {}", id);
                    return new UserNotFoundException(message, id);
                });

        logger.debug("User found with ID: {}", id);
        return this.userMapper.mapUserToUserDto(user);
    }

    @Override
    public UserDto updateUser(Long id, UpdateUserDto updateDto) {
        logger.info("Updating user with ID: {}", id);

        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> {
                    String message = this.messageSource.getMessage("entity.not.found", new Object[]{id}, Locale.getDefault());
                    logger.error("Cannot update, user not found with ID: {}", id);
                    return new UserNotFoundException(message, id);
                });

        this.userMapper.mapUpdateUserDtoToExistingUser(updateDto, existingUser);
        this.userRepository.save(existingUser);

        logger.info("User updated successfully with ID: {}", id);
        return this.userMapper.mapUserToUserDto(existingUser);
    }

    @Override
    public void deleteUser(Long id) {
        logger.info("Deleting user with ID: {}", id);

        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> {
                    String message = this.messageSource.getMessage("entity.not.found", new Object[]{id}, Locale.getDefault());
                    logger.error("Cannot delete, user not found with ID: {}", id);
                    return new UserNotFoundException(message, id);
                });

        this.userRepository.deleteById(id);
        logger.warn("User deleted with ID: {}", id);
    }
}

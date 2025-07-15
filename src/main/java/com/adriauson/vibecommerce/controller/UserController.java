package com.adriauson.vibecommerce.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;


import com.adriauson.vibecommerce.dto.RegisterUserDto;
import com.adriauson.vibecommerce.dto.UpdateUserDto;
import com.adriauson.vibecommerce.dto.UserDto;
import com.adriauson.vibecommerce.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * 📌 D de SOLID: Principio de Inversión de Dependencias (Dependency Inversion Principle)
 *
 * 👉 "Los módulos de alto nivel no deben depender de módulos de bajo nivel. Ambos deben depender de abstracciones."
 * 👉 "Las abstracciones no deben depender de los detalles. Los detalles deben depender de las abstracciones."
 *
 * ✅ Implica que el controlador (u otra clase de alto nivel) debe depender de una interfaz (ej. UserService),
 *    no de una clase concreta (ej. UserServiceImpl).
 *
 * ✅ Ejemplo correcto:
 *    private final UserService userService;
 *
 * ❌ Ejemplo incorrecto:
 *    private final UserServiceImpl userService;
 *
 * ✅ Beneficios:
 *    - Menor acoplamiento
 *    - Código más mantenible y testeable
 *    - Fácil de cambiar implementaciones sin tocar otras capas
 */


/*
 * ❌ Si inyecto UserServiceImpl directamente:
 *    - Rompo el principio de inversión de dependencias.
 *    - Mi controlador queda acoplado a una clase concreta.
 *    - No puedo sustituir fácilmente la implementación.
 *    - Ejemplo: si quiero usar FakeUserService para mockear el service
 *    - devo cambiar el tipo en el constructor y recompilar
 *    - Rompes la inversión de dependencias,
 *    porque una clase de alto nivel (el controller)
 *    depende de una clase concreta (bajo nivel).

 * ✅ Si inyecto la interfaz UserService:
 *    - Aplico D de SOLID (Inversión de dependencias).
 *    - Puedo cambiar la implementación sin tocar el controller.
 *    - Gano testabilidad, flexibilidad y bajo acoplamiento.
 */


@RestController
@RequestMapping("api/v1/users")
public class UserController {
    private final UserService userService;

    public UserController (UserService userService){
        this.userService = userService;
    }
    // http://localhost:8080/api/v1/users
    @PostMapping
    @Operation(
            summary = "Registrar un nuevo usuario",
            description = "Crea un nuevo usuario en el sistema y devuelve los datos creados",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Usuario creado exitosamente"),
                    @ApiResponse(responseCode = "409", description = "El email ya está en uso")
            }
    )

    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody RegisterUserDto registerDto) {
        UserDto userDto = this.userService.registerUser((registerDto));
        // return new ResponseEntity<>(userDto, HttpStatus.CREATED);

        // mas limpio
        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
    }

    @GetMapping
    public ResponseEntity <List<UserDto>> getAllUsers() {
        List <UserDto> userDtoList = this.userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(userDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long id) {
        UserDto userDto = this.userService.getUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }

    // optamos por patch porque actualizamos parcialmente el recurso
    // usaríamos PUT si necesitaramos todas las properties para actualizar el recurso
    @PatchMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @Valid @RequestBody UpdateUserDto updateDto) {
        UserDto updatedUser = this.userService.updateUser(id, updateDto);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        this.userService.deleteUser(id);
        return ResponseEntity.noContent().build(); // 204 no content sin body
    }

}

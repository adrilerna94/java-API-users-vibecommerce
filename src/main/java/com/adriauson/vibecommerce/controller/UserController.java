package com.adriauson.vibecommerce.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import com.adriauson.vibecommerce.dto.RegisterUserDto;
import com.adriauson.vibecommerce.dto.UpdateUserDto;
import com.adriauson.vibecommerce.dto.UserDto;
import com.adriauson.vibecommerce.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * 📌 D of SOLID: Dependency Inversion Principle
 *
 * 👉 "High-level modules should not depend on low-level modules. Both should depend on abstractions."
 * 👉 "Abstractions should not depend on details. Details should depend on abstractions."
 *
 * ✅ Example (correct):
 *    private final UserService userService;
 *
 * ❌ Bad practice:
 *    private final UserServiceImpl userService;
 *
 * ✅ Benefits:
 *    - Lower coupling
 *    - Easier testing and maintenance
 *    - Easier to switch implementations
 */
@Tag(name = "API USERS", description = "Users CRUD")
@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    @Operation(
            summary = "Register a new user",
            description = "Creates a new user in the system and returns the created data",
            responses = {
                    @ApiResponse(responseCode = "201", description = "User successfully created"),
                    @ApiResponse(responseCode = "409", description = "Email already in use"),
                    @ApiResponse(responseCode = "400", description = "Invalid data provided for user creation"),
            }
    )

    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody RegisterUserDto registerDto) {
        UserDto userDto = this.userService.registerUser(registerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
    }

    @GetMapping
    @Operation(
            summary = "Get all users",
            description = "Returns all existing users from the database",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User list retrieved successfully"),
            }
    )
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> userDtoList = this.userService.getAllUsers();
        return ResponseEntity.ok(userDtoList);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get a user by ID",
            description = "Retrieves an existing user by ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User retrieved successfully"),
                    @ApiResponse(responseCode = "404", description = "User not found"),
            }
    )
    public ResponseEntity<UserDto> getUserById(
            @Parameter(description = "ID of the user to retrieve", example = "2")
            @PathVariable("id") Long id) {
        UserDto userDto = this.userService.getUserById(id);
        return ResponseEntity.ok(userDto);
    }

    @PatchMapping("/{id}")
    @Operation(
            summary = "Partially update a user",
            description = "Updates an existing user by ID with partial data and returns the updated user",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User updated successfully"),
                    @ApiResponse(responseCode = "404", description = "User not found"),
                    @ApiResponse(responseCode = "400", description = "Invalid data for partial update"),
            }
    )
    public ResponseEntity<UserDto> updateUser(
            @Parameter(description = "ID of the user to update", example = "3")
            @PathVariable Long id,
            @Valid @RequestBody UpdateUserDto updateDto) {
        UserDto updatedUser = this.userService.updateUser(id, updateDto);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete a user",
            description = "Deletes a user by ID",
            responses = {
                    @ApiResponse(responseCode = "204", description = "User successfully deleted"),
                    @ApiResponse(responseCode = "404", description = "User not found"),
            }
    )
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        this.userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}

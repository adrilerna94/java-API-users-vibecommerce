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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "API USERS", description = "Users CRUD")
@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    @Operation(summary = "Register a new user", description = "Creates a new user in the system and returns the created data",
            responses = {
                    @ApiResponse(responseCode = "201", description = "User successfully created"),
                    @ApiResponse(responseCode = "409", description = "Email already in use"),
                    @ApiResponse(responseCode = "400", description = "Invalid data provided for user creation")
            }
    )
    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody RegisterUserDto registerDto) {
        logger.info("POST /api/v1/users - Creating user with email: {}", registerDto.getEmail());
        UserDto userDto = this.userService.registerUser(registerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
    }

    @GetMapping
    @Operation(summary = "Get all users", description = "Returns all existing users from the database",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User list retrieved successfully")
            }
    )
    public ResponseEntity<List<UserDto>> getAllUsers() {
        logger.info("GET /api/v1/users - Fetching all users");
        List<UserDto> userDtoList = this.userService.getAllUsers();
        logger.debug("Number of users retrieved: {}", userDtoList.size());
        return ResponseEntity.ok(userDtoList);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a user by ID", description = "Retrieves an existing user by ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User retrieved successfully"),
                    @ApiResponse(responseCode = "404", description = "User not found")
            }
    )
    public ResponseEntity<UserDto> getUserById(
            @Parameter(description = "ID of the user to retrieve", example = "2")
            @PathVariable("id") Long id) {
        logger.info("GET /api/v1/users/{} - Fetching user", id);
        UserDto userDto = this.userService.getUserById(id);
        return ResponseEntity.ok(userDto);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Partially update a user", description = "Updates an existing user by ID with partial data and returns the updated user",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User updated successfully"),
                    @ApiResponse(responseCode = "404", description = "User not found"),
                    @ApiResponse(responseCode = "400", description = "Invalid data for partial update")
            }
    )
    public ResponseEntity<UserDto> updateUser(
            @Parameter(description = "ID of the user to update", example = "3")
            @PathVariable Long id,
            @Valid @RequestBody UpdateUserDto updateDto) {
        logger.info("PATCH /api/v1/users/{} - Updating user", id);
        UserDto updatedUser = this.userService.updateUser(id, updateDto);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a user", description = "Deletes a user by ID",
            responses = {
                    @ApiResponse(responseCode = "204", description = "User successfully deleted"),
                    @ApiResponse(responseCode = "404", description = "User not found")
            }
    )
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        logger.warn("DELETE /api/v1/users/{} - Deleting user", id);
        this.userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}

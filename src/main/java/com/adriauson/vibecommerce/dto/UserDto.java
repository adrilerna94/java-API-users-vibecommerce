package com.adriauson.vibecommerce.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 📦 DTO representing user data returned by the API.
 */
@Getter
@Setter
@Schema(
        name = "UserDto",
        description = "User data returned in responses. Contains ID and user details."
)
public class UserDto extends BaseUserDto {

    @Schema(
            description = "Unique identifier of the user",
            example = "42"
    )
    private Long id;
}

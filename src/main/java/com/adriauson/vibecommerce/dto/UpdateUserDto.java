package com.adriauson.vibecommerce.dto;

import com.adriauson.vibecommerce.validation.annotations.AtLeastOneRegisterField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 📦 DTO for updating user data.
 * 🔁 At least one field must be provided (validated with custom annotation).
 */
@Getter
@Setter
@AtLeastOneRegisterField
@Schema(
        name = "UpdateUserDto",
        description = "Data for partially updating an existing user. At least one field must be present."
)
public class UpdateUserDto extends BaseUserDto {
}

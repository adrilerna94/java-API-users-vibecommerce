package com.adriauson.vibecommerce.vibecommerce.dto;
import com.adriauson.vibecommerce.vibecommerce.validator.ValidPassword;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

// Extraer las validaciones a una clase externa

@Getter
@Setter
public class UserDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message= "Firstname is required")
    @Size(min=3, max=30)
    @Column(name= "firstName", nullable = false, length = 30)
    private String firstName;

    @NotBlank(message= "Lastname is required")
    @Size(min=3, max=30)
    @Column(name= "lastName", nullable = false, length = 30)
    private String lastName;

    @Email(message = "Must be a valid email")
    @NotBlank(message = "Email is required")
    @Column(nullable = false, unique = true, length = 50)
    private String email;

    @ValidPassword
    private String password;

    @NotBlank(message = "Address is required")
    @Column(name = "address", nullable = false)
    private String address;
}

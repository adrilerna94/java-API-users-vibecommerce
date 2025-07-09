package com.adriauson.vibecommerce.vibecommerce.dto;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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

    @NotBlank(message = "Password is required")
    @Column(name = "password", nullable = false)
    @Size(min= 5, message = "Password must contain at least 5 characters")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z]).{5,}$",
            message = "Must contain at least one upper and one lower case letter"
    )
    private String password;

    @NotBlank(message = "Address is required")
    @Column(name = "address", nullable = false)
    private String address;
}

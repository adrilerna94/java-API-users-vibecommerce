package com.adriauson.vibecommerce.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Table(name= "users")
@Getter
@Setter
@NoArgsConstructor

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "firstName", nullable = false, length = 30)
    private String firstName;

    @Column(name= "lastName", nullable = false, length = 30)
    private String lastName;

    @Column(name="email", nullable = false, unique = true, length = 50)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "address", nullable = false, length = 250)
    private String address;

}

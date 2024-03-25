package com.example.capstone1.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Table(name = "users")
@Entity
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String id;

    @NotEmpty(message = "Username must not be empty")
    @Size(min = 6, message = "Username must be more than 5 characters long")
    //@Column(columnDefinition = "varchar(20) check(length(username)>5) not null")
    @Column(columnDefinition = "varchar(20) not null")

    private String username;

    @NotEmpty(message = "Password must not be empty")
    @Size(min = 6, message = "Password must be more than 6 characters long and must contain both letters and digits")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9]).*$", message = "Password must contain both letters and digits")
    //@Column(columnDefinition = "varchar(20) check(length(pasword)>5) not null")
    @Column(columnDefinition = "varchar(20) not null")
    private String password;

    @NotEmpty(message = "Email must not be empty")
    @Email(message = "Email must be valid email")
    @Column(columnDefinition = "varchar(40) not null")
    private String email;

    @NotEmpty(message = "Role must not be empty")
    @Pattern(regexp = "^(Admin|Customer)$", message = "Role must be either 'Admin' or 'Customer'")
    //@Column(columnDefinition = "varchar(8) not null check(role='Admin' or role ='Customer')")
    @Column(columnDefinition = "varchar(8) not null")
    private String role;

    @NotNull(message = "Balance must not be empty")
    @Positive(message = "Balance must be positive")
    @Column(columnDefinition = "double not null")
    private Double balance;

}

package com.todo.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;


@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@Data
@Entity
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank
    @Size(min = 3, max =100)
    private String userName;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min = 5, max =50)
    private String passwordHash;

    @Min(value = 12, message = "Age must be at least 12")
    @Max(value = 100, message = "Age cannot exceed 100")
    @NotNull
    private int age;


}

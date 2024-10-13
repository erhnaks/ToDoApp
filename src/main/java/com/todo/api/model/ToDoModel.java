package com.todo.api.model;

import com.todo.api.enums.ToDoStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "todo")
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class ToDoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @NonNull
    @Size(min = 3, max =50)
    private String briefDescription;

    @NonNull
    @Size(min = 10, max =255)
    private String description;

    @NonNull
    @Enumerated(EnumType.STRING)
    private ToDoStatus status;

    @NonNull
    @Column(name = "_time", nullable = false)
    private LocalDateTime dateTime = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NonNull
    private UserModel user;

}

package com.todo.api.dto;

import com.todo.api.enums.ToDoStatus;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class TodoDto {
    @NonNull
    private Long id;

    @NonNull
    @Size(min = 3, max =50)
    private String briefDescription;

    @NonNull
    @Size(min = 10, max =255)
    private String description;

    @NonNull
    private ToDoStatus status;

    @NonNull
    private LocalDateTime dateTime = LocalDateTime.now();

    @NonNull
    private UserDto userId;

    public TodoDto(Long id, String briefDescription, String description, ToDoStatus status, LocalDateTime dateTime, UserDto userId) {
        this.id = id;
        this.briefDescription = briefDescription;
        this.description = description;
        this.status =  status;
        this.dateTime = dateTime;
        this.userId = userId;
    }
}





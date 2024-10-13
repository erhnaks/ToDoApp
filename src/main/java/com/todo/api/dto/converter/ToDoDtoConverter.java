package com.todo.api.dto.converter;

import com.todo.api.dto.TodoDto;
import com.todo.api.dto.UserDto;
import com.todo.api.model.ToDoModel;
import com.todo.api.model.UserModel;
import org.springframework.stereotype.Component;

@Component
public class ToDoDtoConverter {

    private UserDto convertToUserDto(UserModel userModel) {
        return new UserDto(
                userModel.getId(),
                userModel.getUserName(),
                userModel.getEmail(),
                userModel.getAge()
        );
    }

    public TodoDto convertToTodoDto(ToDoModel from) {
        return new TodoDto(
                from.getId(),
                from.getBriefDescription(),
                from.getDescription(),
                from.getStatus(),
                from.getDateTime(),
                convertToUserDto(from.getUser())
             );
    }
}

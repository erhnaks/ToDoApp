package com.todo.api.dto.converter;

import com.todo.api.dto.UserDto;
import com.todo.api.model.UserModel;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConverter {

    public UserDto convertToUserDto(UserModel from) {
        return new
                UserDto(from.getId(),
                from.getUserName(),
                from.getEmail(),
                from.getAge());
    }

}

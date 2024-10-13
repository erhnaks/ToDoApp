package com.todo.api.dto;

import com.todo.api.model.UserModel;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @NonNull
    private Long id;

    @NonNull
    @Size(min = 3, max = 100)
    private String userName;

    @NonNull
    @Email
    private String email;

    @Min(12)
    @Max(100)
    private int age;

    public UserDto mapToUserDto(UserModel userModel) {
        return new UserDto(
                userModel.getId(),
                userModel.getUserName(),
                userModel.getEmail(),
                userModel.getAge()
        );

    }
}
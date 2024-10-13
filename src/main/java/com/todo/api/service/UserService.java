package com.todo.api.service;

import com.todo.api.dto.UserDto;
import com.todo.api.dto.converter.UserDtoConverter;
import com.todo.api.exception.UserNotFoundException;
import com.todo.api.model.ToDoModel;
import com.todo.api.model.UserModel;
import com.todo.api.repository.ToDoRepository;
import com.todo.api.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);  // Correct logger

    private final UserRepository userRepository;
    private final ToDoRepository toDoRepository;
    private final UserDtoConverter converter;

    public UserService(UserRepository userRepository,
                       UserDtoConverter converter,
                       ToDoRepository toDoRepository) {
        this.userRepository = userRepository;
        this.converter = converter;
        this.toDoRepository = toDoRepository;
    }

    protected UserModel findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(converter::convertToUserDto)
                .collect(Collectors.toList());
    }

    public UserDto getUserById(Long userId) {
        return converter.convertToUserDto(findUserById(userId));
    }

    public UserModel saveUser(UserModel user) {
        return userRepository.save(user);
    }
    @Transactional
    public void deleteUser(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("userId cannot be null");
        }

        UserModel userExist = userRepository
                .findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User does not exist with id: " + userId));

        // Delete ToDo records if any
        List<ToDoModel> todoDtoList = toDoRepository.findTodoByUserId(userId);

        if (todoDtoList == null || todoDtoList.isEmpty()) {  // Check both for null and empty
            logger.info("No todos found for user with ID: {}", userId);  // Logging userId correctly
        } else {
            for (ToDoModel toDoModel : todoDtoList) {
                logger.info("Deleting ToDo with ID: {}", toDoModel.getId());  // Logging each Todo ID
            }
            toDoRepository.deleteByUserId(userId);
        }

        // Now delete the user
        userRepository.deleteById(userId);
        logger.info("User with ID: {} has been deleted", userId);  // Logging userId on delete
    }
}

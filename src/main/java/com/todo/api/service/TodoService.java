package com.todo.api.service;

import com.todo.api.dto.TodoDto;
import com.todo.api.dto.UserDto;
import com.todo.api.dto.converter.ToDoDtoConverter;
import com.todo.api.enums.ToDoStatus;
import com.todo.api.exception.ToDoNotFoundException;
import com.todo.api.exception.UserNotFoundException;
import com.todo.api.model.ToDoModel;
import com.todo.api.model.UserModel;
import com.todo.api.repository.ToDoRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TodoService {

    private final ToDoRepository repository;

    private final ToDoDtoConverter converter;

    private final UserService userService;

    public TodoService(ToDoRepository repository, ToDoDtoConverter converter, UserService userService) {
        this.repository = repository;
        this.converter = converter;
        this.userService = userService;
    }


    protected ToDoModel getTodosById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ToDoNotFoundException("To do is not found with id " + id));
    }

    public TodoDto getTodosDtoById(Long id) {
        return converter.convertToTodoDto(getTodosById(id));
    }

    public List<TodoDto> getAllTodos() {
        List<ToDoModel> todos = repository.findAll();

        return todos.stream().map(converter::convertToTodoDto).collect(Collectors.toList());
    }

    @Transactional
    public ToDoModel saveTodo(ToDoModel model) {

        Long userId = Optional.ofNullable(model.getUser()).map(UserModel::getId).orElseThrow(() -> new IllegalArgumentException("User is missing"));

        UserDto user = userService.getUserById(model.getUser().getId());

        if (user == null) {
            throw new UserNotFoundException("User not found with id " + userId);
        }

        if (model == null) {
            throw  new IllegalArgumentException("Model can not be null");
        }

        if (model.getStatus() == null) {
            model.setStatus(ToDoStatus.PENDING);
        }

        return repository.save(model);
    }

    public void deleteTodoById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Todo not found with id " + id);
        }
        log.info("Deleting todo with id " + id);
        repository.deleteById(id);


    }

    @Transactional
    public ToDoModel updateTodoById(Long id, ToDoModel model) {

        ToDoModel currentTodo = repository.findById(id).orElseThrow(() -> new ToDoNotFoundException("Todo not found with id " + id));

        if (model.getBriefDescription() != null) {
            currentTodo.setBriefDescription(model.getBriefDescription());
        }

        if (model.getDescription() != null) {
            currentTodo.setDescription(model.getDescription());
        }

        if (model.getStatus() != null) {
            currentTodo.setStatus(model.getStatus());
        }

        currentTodo.setDateTime(LocalDateTime.now());
        return repository.save(currentTodo);

    }
}

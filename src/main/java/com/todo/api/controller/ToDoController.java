package com.todo.api.controller;

import com.todo.api.dto.TodoDto;
import com.todo.api.model.ToDoModel;
import com.todo.api.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class ToDoController {

    private final TodoService service;

    public ToDoController(TodoService service) {
        this.service = service;
    }

    @GetMapping("{toDoId}")
    public ResponseEntity<TodoDto> getTodosDtoById(@PathVariable Long toDoId) {
        return ResponseEntity.ok(service.getTodosDtoById(toDoId));
    }


    @GetMapping("")
    public ResponseEntity<List<TodoDto>> getAllToDo() {
        List<TodoDto> todos = service.getAllTodos();
        return ResponseEntity.ok(todos);
    }

    @PostMapping("/create")
    public ResponseEntity<ToDoModel> saveTodo(@RequestBody ToDoModel model) {
        ToDoModel toDoToSave = service.saveTodo(model);
        return  ResponseEntity.status(HttpStatus.CREATED).body(toDoToSave);
    }
}

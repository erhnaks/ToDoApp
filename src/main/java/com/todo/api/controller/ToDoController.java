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

    private final TodoService todoService;

    public ToDoController(TodoService service) {
        this.todoService = service;
    }

    @GetMapping("{toDoId}")
    public ResponseEntity<TodoDto> getTodosDtoById(@PathVariable Long toDoId) {
        return ResponseEntity.ok(todoService.getTodosDtoById(toDoId));
    }


    @GetMapping("")
    public ResponseEntity<List<TodoDto>> getAllToDo() {
        List<TodoDto> todos = todoService.getAllTodos();
        return ResponseEntity.ok(todos);
    }

    @PostMapping("/create")
    public ResponseEntity<ToDoModel> saveTodo(@RequestBody ToDoModel model) {
        ToDoModel toDoToSave = todoService.saveTodo(model);
        return  ResponseEntity.status(HttpStatus.CREATED).body(toDoToSave);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Void> deleteTodoById(@PathVariable Long id) {

        todoService.deleteTodoById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ToDoModel> updateTodoById(@PathVariable Long id, @RequestBody ToDoModel model) {
        ToDoModel updatedTodo = todoService.updateTodoById(id, model);
        return ResponseEntity.status(HttpStatus.OK).body(updatedTodo);
    }

}

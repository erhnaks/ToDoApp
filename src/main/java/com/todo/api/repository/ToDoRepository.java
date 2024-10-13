package com.todo.api.repository;

import com.todo.api.model.ToDoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ToDoRepository extends JpaRepository<ToDoModel, Long> {
    void deleteByUserId(Long userId);

    List<ToDoModel> findTodoByUserId(Long userId);
}

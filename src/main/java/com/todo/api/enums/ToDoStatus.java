package com.todo.api.enums;

public enum ToDoStatus {
    PENDING,
    READY,
    IN_PROGRESS,
    COMPLETED,
    CANCELLED;

    public static ToDoStatus getDefault() {
        return PENDING;
    }
}

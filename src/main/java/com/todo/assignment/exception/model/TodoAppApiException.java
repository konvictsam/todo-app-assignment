package com.todo.assignment.exception.model;

import lombok.Data;

@Data
public class TodoAppApiException extends RuntimeException {
    private final String message;

    public TodoAppApiException(String message, Throwable cause, String message1) {
        super(message, cause);
        this.message = message;
    }
}

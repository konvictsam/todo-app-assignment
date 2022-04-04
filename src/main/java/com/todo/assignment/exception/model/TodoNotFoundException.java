package com.todo.assignment.exception.model;

import com.todo.assignment.enums.ErrorCode;

public class TodoNotFoundException extends RuntimeException {
    private final ErrorCode errorCode;

    public TodoNotFoundException(ErrorCode errorCode, Long todoId) {
        super(errorCode.getErrorCode() + " - " + errorCode.getErrorMessage() + " - " + todoId);
        this.errorCode = errorCode;
    }
}

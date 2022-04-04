package com.todo.assignment.exception.model;

import com.todo.assignment.enums.ErrorCode;
import lombok.Data;

@Data
public class UserNotFoundException extends RuntimeException {

    private final ErrorCode errorCode;

    public UserNotFoundException(ErrorCode errorCode, Long userId) {
        super(errorCode.getErrorCode() + " - " + errorCode.getErrorMessage() + " - " + userId);
        this.errorCode = errorCode;
    }
}

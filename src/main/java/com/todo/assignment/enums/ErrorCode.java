package com.todo.assignment.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    INTERNAL_SERVER_ERROR("E101", "System error occurred. Please contact system administrator."),
    USER_NOT_FOUND_EXCEPTION("E102", "Unable to find User"),
    TODO_NOT_FOUND_EXCEPTION("E103", "Unable to find Todo");

    private String errorCode;
    private String errorMessage;
}

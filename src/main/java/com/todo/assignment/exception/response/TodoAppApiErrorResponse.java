package com.todo.assignment.exception.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TodoAppApiErrorResponse {
    private final String errorCode;
    private final String errorMessage;
}

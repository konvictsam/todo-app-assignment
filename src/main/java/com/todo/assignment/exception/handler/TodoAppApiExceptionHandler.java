package com.todo.assignment.exception.handler;

import com.todo.assignment.enums.ErrorCode;
import com.todo.assignment.exception.model.TodoAppApiException;
import com.todo.assignment.exception.model.UserNotFoundException;
import com.todo.assignment.exception.response.TodoAppApiErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.UUID;

@Slf4j
@RestControllerAdvice
public class TodoAppApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(TodoAppApiException.class)
    public ResponseEntity<TodoAppApiErrorResponse> handleTodoAppApiException(TodoAppApiException ex) {
        TodoAppApiErrorResponse todoAppApiErrorResponse = new TodoAppApiErrorResponse(generateErrorCode(), ErrorCode.INTERNAL_SERVER_ERROR.getErrorMessage());
        log.error("Technical Exception occurred. errorCode-{}, error-{}", todoAppApiErrorResponse.getErrorCode(), ex);
        return new ResponseEntity<>(todoAppApiErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<TodoAppApiErrorResponse> handleUserNotFoundException(UserNotFoundException ex) {
        TodoAppApiErrorResponse todoAppApiErrorResponse = new TodoAppApiErrorResponse(ex.getErrorCode().getErrorCode(), ErrorCode.USER_NOT_FOUND_EXCEPTION.getErrorMessage());
        log.error("Business Exception occurred: {}", todoAppApiErrorResponse.getErrorMessage());
        return new ResponseEntity<>(todoAppApiErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<TodoAppApiErrorResponse> handleOtherException(Exception ex) {
        TodoAppApiErrorResponse todoAppApiErrorResponse = new TodoAppApiErrorResponse(generateErrorCode(), ErrorCode.INTERNAL_SERVER_ERROR.getErrorMessage());
        log.error("Technical Exception occurred. errorCode-{}, error-{}", todoAppApiErrorResponse.getErrorCode(), ex);
        return new ResponseEntity<>(todoAppApiErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private String generateErrorCode() {
        return String.valueOf(UUID.randomUUID().hashCode());
    }
}

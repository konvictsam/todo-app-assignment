package com.todo.assignment.service;

import com.todo.assignment.entity.Todo;
import com.todo.assignment.exception.model.TodoNotFoundException;
import com.todo.assignment.repository.ToDoRepository;
import com.todo.assignment.enums.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TodoService {

    @Autowired
    private ToDoRepository toDoRepository;

    public Todo getTodo(Long todoId) {
        log.info("Getting Todo-{}", todoId);
        return toDoRepository.findById(todoId).orElseThrow(() -> new TodoNotFoundException(ErrorCode.TODO_NOT_FOUND_EXCEPTION, todoId));
    }

    public Todo createTodo(Todo todo) {
        log.info("Creating new Todo, todo-id-{}", todo.getId());
        return toDoRepository.save(todo);
    }

    public void deleteTodo(Long todoId) {
        log.warn("Deleting Todo, todo-id-{}", todoId);
        Todo todo = toDoRepository.findById(todoId).orElseThrow(() -> new TodoNotFoundException(ErrorCode.TODO_NOT_FOUND_EXCEPTION, todoId));
        toDoRepository.delete(todo);
    }

    public List<Todo> getAllTodos() {
        log.info("Getting all todos");
        return toDoRepository.findAll();
    }

    public Todo updateTodo(Long todoId, Todo todo) {
        log.info("Updating Todo, todo-id-{}", todoId);
        Todo updatedTodo = toDoRepository.findById(todoId).orElseThrow(() -> new TodoNotFoundException(ErrorCode.TODO_NOT_FOUND_EXCEPTION, todoId));
        BeanUtils.copyProperties(todo, updatedTodo, "id");
        return toDoRepository.save(updatedTodo);
    }
}

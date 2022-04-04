package com.todo.assignment.controller;

import com.todo.assignment.entity.Todo;
import com.todo.assignment.entity.User;
import com.todo.assignment.service.TodoService;
import com.todo.assignment.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@Api(tags = "Todo application REST API")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TodoAppController {

    @Autowired
    UserService userService;

    @Autowired
    TodoService todoService;

    @ApiOperation(value = "Get All Users", response = User.class)
    @GetMapping("/get-users")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @ApiOperation(value = "Get User by Id", response = User.class)
    @GetMapping("/get-user/{userId}")
    public ResponseEntity<User> getUser(@PathVariable Long userId) {
        return new ResponseEntity<>(userService.getUser(userId), HttpStatus.OK);
    }

    @ApiOperation(value = "Add new User", response = User.class)
    @PostMapping("/add-user")
    public ResponseEntity<User> createUser(@RequestBody  User user) {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.OK);
    }

    @ApiOperation(value = "Update User by Id")
    @PostMapping("/update-user/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User user) {
        return new ResponseEntity<>(userService.updateUser(userId, user), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete User by Id")
    @DeleteMapping("/delete-user/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

    @ApiOperation(value = "Get All Todos", response = Todo.class)
    @GetMapping("/get-todos")
    public ResponseEntity<List<Todo>> getAllTodos() {
        return new ResponseEntity<>(todoService.getAllTodos(), HttpStatus.OK);
    }

    @ApiOperation(value = "Get Todo by Id", response = Todo.class)
    @GetMapping("/get-todo/{todoId}")
    public ResponseEntity<Todo> getTodo(@PathVariable Long todoId) {
        return new ResponseEntity<>(todoService.getTodo(todoId), HttpStatus.OK);
    }

    @ApiOperation(value = "Add new Todo", response = Todo.class)
    @PostMapping("/add-todo")
    public ResponseEntity<Todo> createTodo(@RequestBody  Todo todo) {
        return new ResponseEntity<>(todoService.createTodo(todo), HttpStatus.OK);
    }

    @ApiOperation(value = "Update Todo by Id")
    @PostMapping("/update-todo/{todoId}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Long todoId, @RequestBody Todo todo) {
        return new ResponseEntity<>(todoService.updateTodo(todoId, todo), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete Todo by Id")
    @DeleteMapping("/delete-todo/{todoId}")
    public ResponseEntity<String> deleteTodo(@PathVariable Long todoId) {
        todoService.deleteTodo(todoId);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }
}

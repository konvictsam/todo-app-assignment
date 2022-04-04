package com.todo.assignment.controller;

import com.todo.assignment.data.TestDataUtils;
import com.todo.assignment.entity.Todo;
import com.todo.assignment.entity.User;
import com.todo.assignment.enums.ErrorCode;
import com.todo.assignment.exception.model.TodoNotFoundException;
import com.todo.assignment.exception.model.UserNotFoundException;
import com.todo.assignment.repository.ToDoRepository;
import com.todo.assignment.repository.UserRepository;
import com.todo.assignment.service.TodoService;
import com.todo.assignment.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class TodoAppControllerTest {

    @InjectMocks
    TodoAppController todoAppController;

    @Mock
    TodoService todoService;

    @Mock
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Mock
    ToDoRepository toDoRepository;

    @Test
    @DisplayName("Test getAllUsers method if it returns all Users or not")
    void getAllUsers() {
        Mockito.when(userService.getAllUsers()).thenReturn(TestDataUtils.getUsersTestData());
        ResponseEntity<List<User>> response =  todoAppController.getAllUsers();
        Mockito.verify(userService, Mockito.times(1)).getAllUsers();
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test
    @DisplayName("Test getAllUsers method if it throws exception or not")
    void getAllUsers_Error() {
        Mockito.when(userService.getAllUsers()).thenThrow(new RuntimeException("Technical error"));
        try {
            Exception exception = Assertions.assertThrows(RuntimeException.class, () ->{
               todoAppController.getAllUsers();
            });
            assertTrue(exception.getMessage().contains("Technical error"));

        }catch(Exception ae) {
            fail();
        }
    }

    @Test
    @DisplayName("Test getUser method if it return correct user or not")
    void getUser() {
        Mockito.when(userService.getUser(any())).thenReturn(TestDataUtils.getUsersDataById());
        ResponseEntity<User> response =  todoAppController.getUser(1l);
        Mockito.verify(userService, Mockito.times(1)).getUser(any());
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Test001", response.getBody().getUserName());
    }

    @Test
    @DisplayName("Test getUser method if it throws exception or not")
    void getUser_Error() {
        Mockito.when(userService.getUser(any())).thenThrow(new UserNotFoundException(ErrorCode.USER_NOT_FOUND_EXCEPTION, 1l));
        try {
            UserNotFoundException userNotFoundException = Assertions.assertThrows(UserNotFoundException.class, () ->{
                todoAppController.getUser(1l);
            });
            assertTrue(userNotFoundException.getMessage().contains("Unable to find User"));
        }catch(Exception ae) {
            fail();
        }
    }

    @Test
    @DisplayName("Test createUser method if it creates correct user or not")
    void createUser() {
        Mockito.when(userService.createUser(any())).thenReturn(TestDataUtils.getUsersDataById());
        ResponseEntity<User> response =  todoAppController.createUser(TestDataUtils.getUsersDataById());
        Mockito.verify(userService, Mockito.times(1)).createUser(any());
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Test001", response.getBody().getUserName());
    }

    @Test
    @DisplayName("Test updateUser method if it updates correct user or not")
    void updateUser() {
        Mockito.when(userService.updateUser(any(), any())).thenReturn(TestDataUtils.getUsersDataById());
        ResponseEntity<User> response =  todoAppController.updateUser(1l, TestDataUtils.getUsersDataById());
        Mockito.verify(userService, Mockito.times(1)).updateUser(any(), any());
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Test001", response.getBody().getUserName());

    }

    @Test
    @DisplayName("Test updateUser method if it throws exception or not")
    void updateUser_Error() {
        Mockito.when(userService.updateUser(any(), any())).thenThrow(new UserNotFoundException(ErrorCode.USER_NOT_FOUND_EXCEPTION, 1l));
        try {
            UserNotFoundException userNotFoundException = Assertions.assertThrows(UserNotFoundException.class, () ->{
                todoAppController.updateUser(1l, new User());
            });
            assertTrue(userNotFoundException.getMessage().contains("Unable to find User"));
        }catch(Exception ae) {
            fail();
        }
    }

    @Test
    @DisplayName("Test deleteUser method if it deletes User or not")
    void deleteUser() {
        Mockito.doNothing().when(userService).deleteUser(any());
        ResponseEntity<String> response =  todoAppController.deleteUser(1l);
        Mockito.verify(userService, Mockito.times(1)).deleteUser(any());
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("SUCCESS", response.getBody());
    }

    @Test
    @DisplayName("Test getAllTodos method if it returns all Todos or not")
    void getAllTodos() {
        Mockito.when(todoService.getAllTodos()).thenReturn(TestDataUtils.getTodosTestData());
        ResponseEntity<List<Todo>> response =  todoAppController.getAllTodos();
        Mockito.verify(todoService, Mockito.times(1)).getAllTodos();
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test
    @DisplayName("Test getAllTodos method if it throws exception or not")
    void getAllTodos_Error() {
        Mockito.when(todoService.getAllTodos()).thenThrow(new RuntimeException("Technical error"));
        try {
            Exception exception = Assertions.assertThrows(RuntimeException.class, () ->{
                todoAppController.getAllTodos();
            });
            assertTrue(exception.getMessage().contains("Technical error"));

        }catch(Exception ae) {
            fail();
        }
    }

    @Test
    @DisplayName("Test getTodo method if it returns specific Todo or not")
    void getTodo() {
        Mockito.when(todoService.getTodo(any())).thenReturn(TestDataUtils.getTodoDataById());
        ResponseEntity<Todo> response =  todoAppController.getTodo(1l);
        Mockito.verify(todoService, Mockito.times(1)).getTodo(any());
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("TestContent1", response.getBody().getContent());
    }

    @Test
    @DisplayName("Test getTodo method if it throws exception or not")
    void getTodo_Error() {
        Mockito.when(todoService.getTodo(any())).thenThrow(new TodoNotFoundException(ErrorCode.TODO_NOT_FOUND_EXCEPTION, 1l));
        try {
            TodoNotFoundException todoNotFoundException = Assertions.assertThrows(TodoNotFoundException.class, () ->{
                todoAppController.getTodo(1l);
            });
            assertTrue(todoNotFoundException.getMessage().contains("Unable to find Todo"));
        }catch(Exception ae) {
            fail();
        }
    }

    @Test
    @DisplayName("Test createTodo method if it creates proper Todo or not")
    void createTodo() {
        Mockito.when(todoService.createTodo(any())).thenReturn(TestDataUtils.getTodoDataById());
        ResponseEntity<Todo> response =  todoAppController.createTodo(TestDataUtils.getTodoDataById());
        Mockito.verify(todoService, Mockito.times(1)).createTodo(any());
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("TestContent1", response.getBody().getContent());
    }

    @Test
    @DisplayName("Test updateTodo method if it update correct Todo or not")
    void updateTodo() {
        Mockito.when(todoService.updateTodo(any(), any())).thenReturn(TestDataUtils.getTodoDataById());
        ResponseEntity<Todo> response =  todoAppController.updateTodo(1l, TestDataUtils.getTodoDataById());
        Mockito.verify(todoService, Mockito.times(1)).updateTodo(any(), any());
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("TestContent1", response.getBody().getContent());
    }

    @Test
    @DisplayName("Test updateTodo method if it throws exception or not")
    void updateTodo_Error() {
        Mockito.when(todoService.updateTodo(any(), any())).thenThrow(new TodoNotFoundException(ErrorCode.TODO_NOT_FOUND_EXCEPTION, 1l));
        try {
            TodoNotFoundException userNotFoundException = Assertions.assertThrows(TodoNotFoundException.class, () ->{
                todoAppController.updateTodo(1l, new Todo());
            });
            assertTrue(userNotFoundException.getMessage().contains("Unable to find Todo"));
        }catch(Exception ae) {
            fail();
        }
    }

    @Test
    @DisplayName("Test deleteTodo method if it deletes Todo or not")
    void deleteTodo() {
        Mockito.doNothing().when(todoService).deleteTodo(any());
        ResponseEntity<String> response =  todoAppController.deleteTodo(1l);
        Mockito.verify(todoService, Mockito.times(1)).deleteTodo(any());
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("SUCCESS", response.getBody());
    }
}
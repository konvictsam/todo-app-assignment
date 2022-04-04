package com.todo.assignment.service;

import com.todo.assignment.data.TestDataUtils;
import com.todo.assignment.entity.Todo;
import com.todo.assignment.repository.ToDoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class TodoServiceTest {

    @InjectMocks
    TodoService todoService;

    @Mock
    ToDoRepository toDoRepository;

    @Test
    @DisplayName("Test getTodo method if it returns specific Todo or not")
    void getTodo() {
        Mockito.when(toDoRepository.findById(any())).thenReturn(Optional.of(TestDataUtils.getTodoDataById()));
        Todo todo = todoService.getTodo(1l);
        assertNotNull(todo);
        assertEquals("TestContent1", todo.getContent());
    }

    @Test
    @DisplayName("Test createTodo method if it creates proper Todo or not")
    void createTodo() {
        Mockito.when(toDoRepository.save(any())).thenReturn(TestDataUtils.getTodoDataById());
        Todo todo = todoService.createTodo(new Todo());
        assertNotNull(todo);
        assertEquals("TestContent1", todo.getContent());
    }

    @Test
    @DisplayName("Test deleteTodo method if it deletes Todo or not")
    void deleteTodo() {
        Mockito.when(toDoRepository.findById(any())).thenReturn(Optional.of(TestDataUtils.getTodoDataById()));
        Mockito.doNothing().when(toDoRepository).delete(any());
        todoService.deleteTodo(1l);
        Mockito.verify(toDoRepository, Mockito.times(1)).delete(any());
    }

    @Test
    @DisplayName("Test getAllTodos method if it returns all Todos or not")
    void getAllTodos() {
        Mockito.when(toDoRepository.findAll()).thenReturn(TestDataUtils.getTodosTestData());
        List<Todo> todoList = todoService.getAllTodos();
        assertNotNull(todoList);
        assertEquals(2, todoList.size());
    }

    @Test
    @DisplayName("Test updateTodo method if it update correct Todo or not")
    void updateTodo() {
        Mockito.when(toDoRepository.findById(any())).thenReturn(Optional.of(TestDataUtils.getTodoDataById()));
        Mockito.when(toDoRepository.save(any())).thenReturn(TestDataUtils.getTodoDataById());
        Todo todo = todoService.updateTodo(1l, new Todo());
        assertNotNull(todo);
        assertEquals("TestContent1", todo.getContent());
    }
}
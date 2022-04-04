package com.todo.assignment.data;

import com.todo.assignment.entity.Priority;
import com.todo.assignment.entity.Todo;
import com.todo.assignment.entity.User;

import java.time.LocalDate;
import java.util.List;

public class TestDataUtils {
    public static List<User> getUsersTestData() {
        return List.of(
               new User(1l, "Test001", "TestPassword001", List.of(new Todo(1l, "TestContent1", false, Priority.LOW, LocalDate.now()))),
               new User(2l, "Test002", "TestPassword002", List.of(new Todo(2l, "TestContent2", false, Priority.LOW, LocalDate.now())))
        );
    }

    public static User getUsersDataById() {
        return new User(1l, "Test001", "TestPassword001", List.of(new Todo(1l, "TestContent1", false, Priority.LOW, LocalDate.now())));
    }

    public static Todo getTodoDataById() {
        return new Todo(1l, "TestContent1", false, Priority.LOW, LocalDate.now());
    }


    public static List<Todo> getTodosTestData() {
        return List.of(
                new Todo(1l, "TestContent1", false, Priority.LOW, LocalDate.now()),
                new Todo(2l, "TestContent2", false, Priority.LOW, LocalDate.now())
        );
    }
}

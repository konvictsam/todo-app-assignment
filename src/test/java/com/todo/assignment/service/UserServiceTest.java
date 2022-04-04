package com.todo.assignment.service;

import com.todo.assignment.data.TestDataUtils;
import com.todo.assignment.entity.User;
import com.todo.assignment.repository.UserRepository;
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
class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Test
    @DisplayName("Test getUser method if it return correct user or not")
    void getUser() {
        Mockito.when(userRepository.findById(any())).thenReturn(Optional.of(TestDataUtils.getUsersDataById()));
        User user = userService.getUser(1l);
        assertNotNull(user);
        assertEquals("Test001", user.getUserName());
    }

    @Test
    @DisplayName("Test createUser method if it creates correct user or not")
    void createUser() {
        Mockito.when(userRepository.save(any())).thenReturn(TestDataUtils.getUsersDataById());
        User user = userService.createUser(new User());
        assertNotNull(user);
        assertEquals("Test001", user.getUserName());
    }

    @Test
    @DisplayName("Test deleteUser method if it deletes User or not")
    void deleteUser() {
        Mockito.when(userRepository.findById(any())).thenReturn(Optional.of(TestDataUtils.getUsersDataById()));
        Mockito.doNothing().when(userRepository).delete(any());
        userService.deleteUser(1l);
        Mockito.verify(userRepository, Mockito.times(1)).delete(any());
    }

    @Test
    @DisplayName("Test getAllUsers method if it returns all Users or not")
    void getAllUsers() {
        Mockito.when(userRepository.findAll()).thenReturn(TestDataUtils.getUsersTestData());
        List<User> userList = userService.getAllUsers();
        assertNotNull(userList);
        assertEquals(2, userList.size());
    }

    @Test
    @DisplayName("Test updateUser method if it updates correct user or not")
    void updateUser() {
        Mockito.when(userRepository.findById(any())).thenReturn(Optional.of(TestDataUtils.getUsersDataById()));
        Mockito.when(userRepository.save(any())).thenReturn(TestDataUtils.getUsersDataById());
        User user = userService.updateUser(1l, new User());
        assertNotNull(user);
        assertEquals("Test001", user.getUserName());
    }
}
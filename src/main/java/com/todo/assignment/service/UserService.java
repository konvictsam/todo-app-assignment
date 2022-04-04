package com.todo.assignment.service;

import com.todo.assignment.entity.User;
import com.todo.assignment.exception.model.UserNotFoundException;
import com.todo.assignment.repository.UserRepository;
import com.todo.assignment.enums.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUser(Long userId) {
        log.info("Getting user-{}", userId);
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(ErrorCode.USER_NOT_FOUND_EXCEPTION, userId));
    }

    public User createUser(User user) {
        log.info("Creating new user, user-name-{}", user.getUserName());
        return userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        log.warn("Deleting user, user-id-{}", userId);
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(ErrorCode.USER_NOT_FOUND_EXCEPTION, userId));
        userRepository.delete(user);
    }

    public List<User> getAllUsers() {
        log.info("Getting all users");
        return userRepository.findAll();
    }

    public User updateUser(Long userId, User user){
        log.info("Updating user, user-id-{}", userId);
        User updatedUser = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(ErrorCode.USER_NOT_FOUND_EXCEPTION, userId));
        BeanUtils.copyProperties(user, updatedUser, "id", "todoList.id");
        return userRepository.save(updatedUser);
    }
}

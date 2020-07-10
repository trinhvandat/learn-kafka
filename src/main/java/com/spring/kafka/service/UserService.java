package com.spring.kafka.service;

import com.spring.kafka.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User createNewUser(User created);

    User updateUser(User updated);

    List<User> findAllUser();

    User deleteUserById(int userId);

    User findUserById(int userId);

}

package com.spring.kafka.service;

import com.spring.kafka.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User create(User created);

    User update(User updated);

    List<User> findAll();

    User deleteById(int userId);

    User findById(int userId);

}
